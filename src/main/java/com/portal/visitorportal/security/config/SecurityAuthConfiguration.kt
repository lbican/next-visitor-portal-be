package com.portal.visitorportal.security.config

import com.portal.visitorportal.repository.user.UserRepository
import com.portal.visitorportal.security.jwt.JwtProperties
import com.portal.visitorportal.service.auth.UserDetailsServiceImpl
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableConfigurationProperties(JwtProperties::class)
open class SecurityAuthConfiguration {

    @Bean
    open fun userDetailsService(userRepository: UserRepository): UserDetailsService =
        UserDetailsServiceImpl(userRepository)

    @Bean open fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    open fun authenticationProvider(userRepository: UserRepository): AuthenticationProvider =
        DaoAuthenticationProvider().also {
            it.setUserDetailsService(userDetailsService(userRepository))
            it.setPasswordEncoder(passwordEncoder())
        }

    @Bean
    open fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager =
        config.authenticationManager
}
