package com.ci.hub.exception

import com.ci.hub.common.HttpStatus

/**
 * @author Kevin
 * @version 1.0
 * @since 2024/05/10
 */
class ServiceException(override val message: String, val code: Int = HttpStatus.INTERNAL_SERVER_ERROR) :
    RuntimeException(message)