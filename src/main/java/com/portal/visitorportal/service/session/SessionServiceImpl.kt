package com.portal.visitorportal.service.session

import com.portal.visitorportal.model.user.UserSession
import com.portal.visitorportal.repository.user.UserRepository
import com.portal.visitorportal.repository.user.UserSessionRepository
import com.portal.visitorportal.security.jwt.JwtProperties
import java.time.LocalDateTime
import kotlin.time.DurationUnit
import kotlin.time.toDuration
import org.springframework.stereotype.Service

@Service
class SessionServiceImpl(
    private val jwtProperties: JwtProperties,
    private val userRepository: UserRepository,
    private val userSessionRepository: UserSessionRepository,
) : SessionService {
    override fun logUserSession(username: String, accessToken: String) {
        val applicationUser = userRepository.getUserByUsername(username)

        if (applicationUser != null) {
            val currentTime = LocalDateTime.now()
            val expiresAt =
                currentTime.plusSeconds(
                    jwtProperties.accessTokenExpiration
                        .toDuration(DurationUnit.SECONDS)
                        .inWholeSeconds
                )

            val userSession =
                UserSession(
                    id = 0, // Will be auto-generated
                    applicationUser = applicationUser,
                    sessionToken = accessToken,
                    expiresAt = expiresAt,
                    lastAccessed = currentTime,
                    createdAt = currentTime,
                )

            userSessionRepository.save(userSession)
        }
    }
}
