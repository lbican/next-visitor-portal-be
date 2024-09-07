package com.portal.visitorportal.service.user

import com.portal.visitorportal.model.user.ApplicationUser
import com.portal.visitorportal.model.user.RegisterCommandDTO
import com.portal.visitorportal.model.user.ApplicationUserDTO
import com.portal.visitorportal.repository.user.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
): UserService {
    override fun getUserByUsername(username: String): ApplicationUserDTO {
        return userRepository.getUserByUsername(username)?.toDto() ?: throw UsernameNotFoundException("User not found")
    }

    override fun registerUser(registerCommandDTO: RegisterCommandDTO): ApplicationUserDTO {
        val user = mapStudentCommandToUser(registerCommandDTO)

        return userRepository.save(user).toDto()
    }

    private fun mapStudentCommandToUser(registerCommandDTO: RegisterCommandDTO): ApplicationUser {
        val passwordHash = passwordEncoder.encode(registerCommandDTO.password)
        val applicationUser = ApplicationUser(
            email = registerCommandDTO.email,
            username = registerCommandDTO.username,
            passwordHash = passwordHash,
            firstName = registerCommandDTO.firstName,
            lastName = registerCommandDTO.lastName
        )

        return applicationUser;
    }
}