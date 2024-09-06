package com.portal.visitorportal.security.jwt

import org.springframework.security.core.userdetails.UserDetails
import java.util.*

interface JwtTokenService {
    fun generate(userDetails: UserDetails, expirationDate: Date, additionalClaims: Map<String, Any> = emptyMap()): String
    fun isValid(token: String, userDetails: UserDetails): Boolean
    fun extractUsername(token: String): String?
    fun isExpired(token: String): Boolean
}