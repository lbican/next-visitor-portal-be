package com.portal.visitorportal.service.user

import com.portal.visitorportal.model.user.ApplicationUser
import com.portal.visitorportal.model.user.ApplicationUserDTO
import com.portal.visitorportal.model.user.commands.RegisterCommandDTO
import com.portal.visitorportal.model.user.commands.UpdateApplicationUserCommandDTO
import com.portal.visitorportal.repository.user.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
open class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) : UserService {
    override fun fetchUser(username: String): ApplicationUserDTO {
        return userRepository.getUserByUsername(username)?.toDto()
            ?: throw UsernameNotFoundException("User not found")
    }

    override fun registerUser(registerCommandDTO: RegisterCommandDTO): ApplicationUserDTO {
        val user = mapStudentCommandToUser(registerCommandDTO)

        return userRepository.save(user).toDto()
    }

    override fun updateUser(username: String, updateApplicationUserCommandDTO: UpdateApplicationUserCommandDTO): ApplicationUserDTO {
        val existingUser = userRepository.getUserByUsername(username)?: throw UsernameNotFoundException("User not found")

        val updatedUser = existingUser.copy(
            email = updateApplicationUserCommandDTO.email,
            firstName = updateApplicationUserCommandDTO.firstName,
            lastName = updateApplicationUserCommandDTO.lastName,
            profilePicture = updateApplicationUserCommandDTO.profilePicture,
            website = updateApplicationUserCommandDTO.website,
            bio = updateApplicationUserCommandDTO.bio,
            location = updateApplicationUserCommandDTO.location,
            phoneNumber = updateApplicationUserCommandDTO.phoneNumber,
            updatedAt = LocalDateTime.now(), // TODO - Make this automatic in DB on any update
        )

        return userRepository.save(updatedUser).toDto()
    }

    private fun mapStudentCommandToUser(registerCommandDTO: RegisterCommandDTO): ApplicationUser {
        val passwordHash = passwordEncoder.encode(registerCommandDTO.password)
        val applicationUser =
            ApplicationUser(
                email = registerCommandDTO.email,
                username = registerCommandDTO.username,
                passwordHash = passwordHash,
                firstName = registerCommandDTO.firstName,
                lastName = registerCommandDTO.lastName,
            )

        return applicationUser
    }
}
