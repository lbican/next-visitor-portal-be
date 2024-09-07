package com.portal.visitorportal.service.auth

import com.portal.visitorportal.model.auth.AuthResponseDTO
import com.portal.visitorportal.model.auth.AuthRequestDTO

interface AuthService {
    fun authenticate(authRequestDTO: AuthRequestDTO): AuthResponseDTO
    fun refreshAccessToken(refreshToken: String): String?
}