package com.ci.hub.common

/**
 * HttpStatus对象包含了一系列HTTP协议中常见的状态码。
 * 这些状态码被用于表示HTTP请求的处理结果。
 */
object HttpStatus {
    // 表示请求已成功处理。
    const val OK = 200

    // 表示请求包含错误语法或者无法被服务器理解。
    const val BAD_REQUEST = 400

    // 表示请求需要用户验证。此状态码用于认证请求头中包含的认证信息不正确的情况。
    const val UNAUTHORIZED = 401

    // 表示服务器拒绝请求，因为请求者没有权限。
    const val FORBIDDEN = 403

    // 表示服务器找不到请求的资源。
    const val NOT_FOUND = 404

    // 表示服务器在处理请求时发生了不可预期的错误。
    const val INTERNAL_SERVER_ERROR = 500
}

object GroupType {
    const val ADMIN = 1 // 表示管理员用户
    const val DEFAULT = 2 // 表示普通用户
    const val VIP = 3 // 表示VIP用户
}

const val WX = "wx"
const val AD = "admin"
const val CI = "ci-hub"