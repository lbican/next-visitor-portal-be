package com.portal.visitorportal.controllers.auth

import com.portal.visitorportal.model.user.dto.AuthRequest
import com.portal.visitorportal.model.user.dto.AuthResponse
import com.portal.visitorportal.model.user.dto.JwtTokenRefreshRequest
import com.portal.visitorportal.model.user.dto.JwtTokenResponse
import com.portal.visitorportal.service.auth.AuthService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
) {
    @PostMapping
    fun authenticate(@Valid @RequestBody authRequest: AuthRequest): AuthResponse {
        return authService.authenticate(authRequest)
    }

    @PostMapping("/refresh")
    fun refreshAccessToken(
        @RequestBody request: JwtTokenRefreshRequest
    ): JwtTokenResponse =
        authService.refreshAccessToken(request.token)
            ?.mapToTokenResponse()
            ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid refresh token.")

    private fun String.mapToTokenResponse(): JwtTokenResponse {
        return JwtTokenResponse(
            token = this
        )
    }
}