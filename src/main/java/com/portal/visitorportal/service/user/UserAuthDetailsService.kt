package com.portal.visitorportal.service.user

import com.portal.visitorportal.model.user.ApplicationUser
import com.portal.visitorportal.repository.user.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserAuthDetailsService(
    private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val applicationUser: ApplicationUser = userRepository.getUserByUsername(username) ?: throw UsernameNotFoundException("User with $username not found")

        return User
            .withUsername(applicationUser.username)
            .password(applicationUser.password)
            .roles(*applicationUser.roles.map { it.roleName }.toTypedArray())
            .build()
    }
}