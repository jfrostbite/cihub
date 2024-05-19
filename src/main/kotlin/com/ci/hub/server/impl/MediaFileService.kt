package com.ci.hub.server.impl

import cn.hutool.core.date.DateUtil
import cn.hutool.core.io.FileUtil
import cn.hutool.core.util.StrUtil
import com.ci.hub.entity.MediaFile
import com.ci.hub.exception.ServiceException
import com.ci.hub.mapper.MediaFileMapper
import com.ci.hub.server.IFileService
import com.ci.hub.server.IMediaFileService
import com.mybatisflex.kotlin.extensions.kproperty.eq
import com.mybatisflex.spring.service.impl.ServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.boot.system.ApplicationHome
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File

/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/17
 */
val ROOT_DIR = File(ApplicationHome(MediaFileService::class.java).dir.absoluteFile, "upload")

@Service
class MediaFileService() : ServiceImpl<MediaFileMapper, MediaFile>(), IFileService, IMediaFileService {

    override suspend fun saveFileAsync(multipartFile: MultipartFile): File = withContext(Dispatchers.IO) {
        val fileName = multipartFile.originalFilename
        val mainName = FileUtil.mainName(fileName)
        val extName = FileUtil.extName(fileName)
        if (extName.isNullOrBlank()) {
            throw ServiceException("文件格式不正确")
        }


        if (!ROOT_DIR.exists()) {
            ROOT_DIR.mkdirs()
        }
        File(ROOT_DIR, DateUtil.current().toString() + StrUtil.DOT + extName).apply {
            multipartFile.transferTo(this)
        }
    }

    override fun saveFile(multipartFile: MultipartFile, exec: (t: MediaFile) -> Unit): MediaFile {
        val fileName = multipartFile.originalFilename
        val mainName = FileUtil.mainName(fileName)
        val extName = FileUtil.extName(fileName)
        val mimeType = FileUtil.getMimeType(fileName)
        if (extName.isNullOrBlank()) {
            throw ServiceException("文件格式不正确")
        }
        if (!ROOT_DIR.exists()) {
            ROOT_DIR.mkdirs()
        }
        return File(ROOT_DIR, DateUtil.current().toString() + StrUtil.DOT + extName).let {
            val mediaFile = MediaFile().apply {
                this.name = mainName
                this.ext = extName
                this.type = mimeType
                this.path = it.name
                exec(this)
            }
            createMedia(mediaFile).let { res ->
                if (res)
                    multipartFile.transferTo(it)
            }
            mediaFile
        }
    }

    override fun saveFileById(
        userId: Long?,
        companyId: Long?,
        industryId: Long?,
        isLogo: Boolean,
        multipartFile: MultipartFile
    ): MediaFile {
        return saveFile(multipartFile) {
            it.userId = userId
            it.companyId = companyId
            it.industryId = industryId
            it.isLogo = isLogo
        }
    }

    override fun createMedia(mediaFile: MediaFile): Boolean =
        mapper.insertOrUpdate(mediaFile) > 0


    override fun updateMedia(mediaFile: MediaFile): Boolean =
        mapper.update(mediaFile) > 0

    override fun deleteMedia(mediaId: Long): Boolean {
        mapper.selectOneById(mediaId)?.apply {
            deleted = true
        }?.let { return mapper.update(it) > 0 } ?: return false
    }

    override fun getMediaLst(): List<MediaFile> {
        TODO("Not yet implemented")
    }

    override fun getMediaByUserIcon(userId: Long): MediaFile {
        return mapper.selectOneByCondition(MediaFile::userId.eq(userId))
    }

    override fun getMediaByUserId(userId: Long): List<MediaFile> {
        return mapper.selectListByCondition(MediaFile::userId.eq(userId))
    }

    override fun getMediaByIndustryId(industryId: Long): List<MediaFile> {
        return mapper.selectListByCondition(MediaFile::industryId.eq(industryId))
    }

    override fun getMediaByCompanyId(companyId: Long): List<MediaFile> {
        return mapper.selectListByCondition(MediaFile::companyId.eq(companyId))
    }
}