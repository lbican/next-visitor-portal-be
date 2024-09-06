package com.portal.visitorportal.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.filter.RequestContextFilter

@EnableWebSecurity
@Configuration
open class SecurityConfig(private val authUserDetailsService: UserDetailsService) {

    @Bean
    open fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    open fun filterChain(http: HttpSecurity, requestContextFilter: RequestContextFilter): SecurityFilterChain {
        http
            .cors { cors -> cors.disable() } // TODO - Enable if needed
            .csrf { csrf -> csrf.disable() } // TODO - Enable if needed
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/api/public/**").permitAll() // Public endpoints
                    .anyRequest().authenticated() // Other endpoints are secured
            }
            .securityMatchers { matchers -> matchers.anyRequest() }
            .httpBasic { httpBasic -> httpBasic.disable() }
        return http.build()
    }
}
