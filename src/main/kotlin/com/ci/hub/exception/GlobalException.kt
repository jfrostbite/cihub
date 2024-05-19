package com.ci.hub.exception

import com.ci.hub.common.HttpStatus
import com.ci.hub.common.Result
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/10
 */
@ControllerAdvice
class GlobalException {

    @ExceptionHandler(ServiceException::class)
    @ResponseBody
    fun serviceException(e: ServiceException): Result<String> = Result.Exception(e.code, e.message)


    @ExceptionHandler(Exception::class)
    @ResponseBody
    fun exception(e: Exception): Result<String> {
        e.printStackTrace()
        return Result.Exception(HttpStatus.INTERNAL_SERVER_ERROR, e.message ?: "服务器异常")
    }

}