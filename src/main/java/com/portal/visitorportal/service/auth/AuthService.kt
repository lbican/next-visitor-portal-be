package com.portal.visitorportal.service.auth

import com.portal.visitorportal.model.user.dto.AuthResponse
import com.portal.visitorportal.model.user.dto.AuthRequest

interface AuthService {
    fun authenticate(authRequest: AuthRequest): AuthResponse
    fun refreshAccessToken(refreshToken: String): String?
}