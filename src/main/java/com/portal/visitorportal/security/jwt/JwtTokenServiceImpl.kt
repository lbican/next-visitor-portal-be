package com.portal.visitorportal.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtTokenServiceImpl(
    jwtProperties: JwtProperties
): JwtTokenService {

    private val secretKey = Keys.hmacShaKeyFor(
        jwtProperties.key.toByteArray()
    )

    override fun generate(
        userDetails: UserDetails,
        expirationDate: Date,
        additionalClaims: Map<String, Any>
    ): String =
        Jwts.builder()
            .claims()
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(expirationDate)
            .add(additionalClaims)
            .and()
            .signWith(secretKey)
            .compact()

    override fun isValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)

        return userDetails.username == username && !isExpired(token)
    }

    override fun extractUsername(token: String): String? {
        return getAllClaims(token).subject
    }

    override fun isExpired(token: String): Boolean {
        val expiration = getAllClaims(token).expiration
        return expiration.before(Date())
    }

    private fun getAllClaims(token: String): Claims {
        val parser = Jwts.parser()
            .verifyWith(secretKey)
            .build()

        return parser.parseSignedClaims(token).payload
    }
}