package com.portal.visitorportal.security

import com.portal.visitorportal.security.model.AuthRequestDTO
import com.portal.visitorportal.security.model.AuthResponseDTO

interface AuthService {
    fun authenticate(authRequestDTO: AuthRequestDTO): AuthResponseDTO

    fun refreshAccessToken(refreshToken: String): String?
}
