package com.portal.visitorportal.user

import com.portal.visitorportal.security.jwt.JwtProperties
import com.portal.visitorportal.user.model.UserSession
import java.time.LocalDateTime
import kotlin.time.DurationUnit
import kotlin.time.toDuration
import org.springframework.stereotype.Service

@Service
class UserSessionServiceImpl(
    private val jwtProperties: JwtProperties,
    private val userRepository: UserRepository,
    private val userSessionRepository: UserSessionRepository,
) : UserSessionService {
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
