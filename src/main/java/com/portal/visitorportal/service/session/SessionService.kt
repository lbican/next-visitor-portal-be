package com.portal.visitorportal.service.session

interface SessionService {
    fun logUserSession(username: String, accessToken: String)
}
