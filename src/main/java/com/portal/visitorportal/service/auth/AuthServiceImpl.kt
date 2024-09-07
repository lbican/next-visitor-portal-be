package com.portal.visitorportal.service.auth

import com.portal.visitorportal.repository.auth.JwtTokenRefreshRepository
import com.portal.visitorportal.model.auth.AuthResponseDTO
import com.portal.visitorportal.model.auth.AuthRequestDTO
import com.portal.visitorportal.security.jwt.JwtProperties
import com.portal.visitorportal.security.jwt.JwtTokenService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthServiceImpl(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val jwtTokenService: JwtTokenService,
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: JwtTokenRefreshRepository
): AuthService {
    override fun authenticate(authRequestDTO: AuthRequestDTO): AuthResponseDTO {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequestDTO.username,
                authRequestDTO.password
            )
        )
        val user = userDetailsService.loadUserByUsername(authRequestDTO.username)
        val accessToken = createAccessToken(user)
        val refreshToken = createRefreshToken(user)

        return AuthResponseDTO(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    override fun refreshAccessToken(refreshToken: String): String? {
        val extractedUsername = jwtTokenService.extractUsername(refreshToken)
        return extractedUsername?.let { email ->
            val currentUserDetails = userDetailsService.loadUserByUsername(email)
            val refreshTokenUserDetails = refreshTokenRepository.findUserDetailsByToken(refreshToken)
            if (!jwtTokenService.isExpired(refreshToken) && refreshTokenUserDetails?.username == currentUserDetails.username)
                createAccessToken(currentUserDetails)
            else
                null
        }
    }

    private fun createAccessToken(user: UserDetails): String {
        return jwtTokenService.generate(
            userDetails = user,
            expirationDate = getAccessTokenExpiration()
        )
    }

    private fun createRefreshToken(user: UserDetails): String {
        return jwtTokenService.generate(
            userDetails = user,
            expirationDate = getRefreshTokenExpiration()
        )
    }

    private fun getAccessTokenExpiration(): Date {
        return Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
    }

    private fun getRefreshTokenExpiration(): Date {
        return Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpiration)
    }
}