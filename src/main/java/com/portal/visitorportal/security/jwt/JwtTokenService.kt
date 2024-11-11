package com.portal.visitorportal.security.jwt

import java.util.*
import org.springframework.security.core.userdetails.UserDetails

interface JwtTokenService {
    fun generate(
        userDetails: UserDetails,
        expirationDate: Date,
        additionalClaims: Map<String, Any> = emptyMap(),
    ): String

    fun isValid(token: String, userDetails: UserDetails): Boolean

    fun extractUsername(token: String): String?

    fun isExpired(token: String): Boolean
}
