package com.ci.hub.common

/**
 * 这里是对该类的简要描述，可以包括主要功能、用途等信息
 *
 * @author Kevin
 * @version 1.0
 * @since 2024/05/06
 */
open class Result<T> {

    open var code: Int = 0
    open var message: String = ""
    open var data: T? = null
    open var success: Boolean = false
    val timestamp: Long = System.currentTimeMillis()


    data class Success<T>(
        override var code: Int,
        override var message: String,
        override var data: T?,
        override var success: Boolean = true
    ) : Result<T>() {
        constructor(data: T?) : this(HttpStatus.OK, "success", data)
    }

    data class Error<T>(
        override var code: Int = HttpStatus.BAD_REQUEST,
        override var message: String = "请求失败",
        override var data: T? = null,
        override var success: Boolean = false
    ) : Result<T>() {
        constructor(message: String) : this(HttpStatus.BAD_REQUEST, message, null)
    }

    data class Exception(
        override var code: Int,
        override var message: String,
        override var data: String?,
        override var success: Boolean = false
    ) : Result<String>() {
        constructor(message: String) : this(HttpStatus.INTERNAL_SERVER_ERROR, message, null)
        constructor(code: Int, message: String): this(code, message, null)
    }


    data class UnAuthorized(
        override var code: Int = HttpStatus.UNAUTHORIZED,
        override var message: String = "未授权",
        override var data: String? = "未授权",
        override var success: Boolean = false
    ) : Result<String>() {
        constructor(message: String) : this(HttpStatus.UNAUTHORIZED, message, null)
    }
}