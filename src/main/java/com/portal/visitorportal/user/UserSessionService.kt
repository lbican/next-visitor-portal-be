package com.portal.visitorportal.user

interface UserSessionService {
    fun logUserSession(username: String, accessToken: String)
}
