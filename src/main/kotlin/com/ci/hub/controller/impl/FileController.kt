package com.ci.hub.controller.impl

import cn.hutool.core.io.FileUtil
import com.ci.hub.common.AuthAccess
import com.ci.hub.common.Result
import com.ci.hub.controller.IFileController
import com.ci.hub.exception.ServiceException
import com.ci.hub.server.IFileService
import com.ci.hub.server.impl.ROOT_DIR
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.util.UriUtils
import java.io.File
import java.net.URL

/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/17
 */
@RestController
@RequestMapping("file")
class FileController : IFileController {

    @Value("\${server.address}")
    override lateinit var host: String

    @Value("\${server.port}")
    override lateinit var port: String

    @Value("\${server.servlet.context-path}")
    override lateinit var path: String

    @Autowired
    lateinit var fileService: IFileService

    override suspend fun uploadFileAsync(@RequestBody vararg files: MultipartFile): Result<List<String>> {
        val fileList = files.map {
            if (it.isEmpty) {
                throw ServiceException("文件不能为空")
            } else {
                fileService.saveFileAsync(it)
            }
        }.map {
            it.absolutePath
        }
        return Result.Success(fileList)
    }

//    @PostMapping("upload")
//    @AuthAccess
    override fun uploadFile(vararg file: MultipartFile): Result<List<String>> {
        val fileList = file.map {
            if (it.isEmpty) {
                throw ServiceException("文件不能为空")
            } else {
                fileService.saveFile(it)
            }
        }.map {
            val file = File(it.path)
            URL("http", "$host", port.toInt(), "${path}/file/download/${FileUtil.getName(file)}").toString()
        }
        return Result.Success(fileList)
    }

    @GetMapping("img/{fileName}")
    @AuthAccess
    override fun downloadFile(@PathVariable fileName: String, response: HttpServletResponse) {
        if (!File(ROOT_DIR, fileName).exists()) {
            return
        }
        response.setHeader("Content-Disposition", "inline;filename=${UriUtils.encode(fileName, "utf-8")}")
        response.setHeader("Content-Length", File(ROOT_DIR, fileName).length().toString())
        response.outputStream.use {
            File(ROOT_DIR, fileName).inputStream().copyTo(it)
        }
    }
}