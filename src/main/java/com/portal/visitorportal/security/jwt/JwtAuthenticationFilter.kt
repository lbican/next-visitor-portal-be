package com.portal.visitorportal.security.jwt

import com.portal.visitorportal.service.auth.UserDetailsServiceImpl
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val userDetailsService: UserDetailsService,
    private val jwtTokenService: JwtTokenService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")

        if (authHeader.isBearerToken().not()) {
            filterChain.doFilter(request, response)
            return
        }

        val jwtToken = authHeader.extractToken()
        val username = jwtTokenService.extractUsername(jwtToken)

        if (username != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = userDetailsService.loadUserByUsername(username)

            if (jwtTokenService.isValid(jwtToken, userDetails)) {
                setAuthentication(userDetails, request)
            }
        }

        filterChain.doFilter(request, response)
    }

    private fun String?.isBearerToken() = this?.startsWith("Bearer ") == true

    private fun String.extractToken() = this.substringAfter("Bearer ")

    private fun setAuthentication(userDetails: UserDetails, request: HttpServletRequest) {
        val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
        SecurityContextHolder.getContext().authentication = authentication
    }
}
