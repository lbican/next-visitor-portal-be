package com.portal.visitorportal.security

import com.portal.visitorportal.security.jwt.JwtTokenRefreshRequestDTO
import com.portal.visitorportal.security.jwt.JwtTokenResponseDTO
import com.portal.visitorportal.security.model.AuthRequestDTO
import com.portal.visitorportal.security.model.AuthResponseDTO
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/auth")
class AuthController(private val authService: AuthService) {
    @PostMapping("/authenticate")
    fun authenticate(@Valid @RequestBody authRequestDTO: AuthRequestDTO): AuthResponseDTO {
        return authService.authenticate(authRequestDTO)
    }

    @PostMapping("/refresh")
    fun refreshAccessToken(@RequestBody request: JwtTokenRefreshRequestDTO): JwtTokenResponseDTO =
        authService.refreshAccessToken(request.token)?.mapToTokenResponse()
            ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid refresh token.")

    private fun String.mapToTokenResponse(): JwtTokenResponseDTO {
        return JwtTokenResponseDTO(token = this)
    }
}
