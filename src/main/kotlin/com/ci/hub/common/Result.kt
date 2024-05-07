package com.ci.hub.common

/**
 * 这里是对该类的简要描述，可以包括主要功能、用途等信息
 *
 * @author Kevin
 * @version 1.0
 * @since 2024/05/06
 */
sealed class Result<T> {

    open var code: Int = 0
    open var message: String? = null
    open var data: T? = null


    data class Success<T>(override var data: T?) : Result<T>() {
        override var code: Int = HttpStatus.OK
        override var message: String? = null
    }

    data class Error(override var message: String? = "请求失败", override var data: String? = "请求失败") :
        Result<String>() {
        override var code: Int = HttpStatus.INTERNAL_SERVER_ERROR
    }

    data class Exception(override var message: String? = "请求异常", override var data: String? = "请求异常") :
        Result<String>() {
        override var code: Int = HttpStatus.INTERNAL_SERVER_ERROR
    }


    data class UnAuthorized(override var message: String? = "未授权", override var data: String? = "未授权") :
        Result<String>() {
        override var code: Int = HttpStatus.UNAUTHORIZED
    }

    data class Forbidden(override var message: String? = "无权限", override var data: String? = "无权限") :
        Result<String>() {
        override var code: Int = HttpStatus.FORBIDDEN
    }
}