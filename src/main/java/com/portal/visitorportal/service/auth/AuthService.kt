package com.portal.visitorportal.service.auth

import com.portal.visitorportal.model.auth.AuthRequestDTO
import com.portal.visitorportal.model.auth.AuthResponseDTO

interface AuthService {
    fun authenticate(authRequestDTO: AuthRequestDTO): AuthResponseDTO

    fun refreshAccessToken(refreshToken: String): String?
}
