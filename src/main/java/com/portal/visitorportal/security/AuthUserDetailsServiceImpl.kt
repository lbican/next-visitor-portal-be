package com.portal.visitorportal.security

import com.portal.visitorportal.user.model.ApplicationUser
import com.portal.visitorportal.user.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthUserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val applicationUser: ApplicationUser =
            userRepository.getUserByUsername(username)
                ?: throw UsernameNotFoundException("User with $username not found")

        return User.builder()
            .username(applicationUser.username)
            .password(applicationUser.password)
            .roles(*applicationUser.roles.map { it.roleName }.toTypedArray())
            .build()
    }
}
