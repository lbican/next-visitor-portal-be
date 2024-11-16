package com.portal.visitorportal.user

import com.portal.visitorportal.exceptions.UserRoleNotFoundException
import com.portal.visitorportal.user.model.ApplicationUser
import com.portal.visitorportal.user.model.ApplicationUserDTO
import com.portal.visitorportal.user.model.UserRole
import com.portal.visitorportal.user.model.commands.RegisterCommandDTO
import com.portal.visitorportal.user.model.commands.UpdateApplicationUserCommandDTO
import java.time.LocalDateTime
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
open class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val roleRepository: UserRoleRepository,
) : UserService {
    override fun fetchUser(username: String): ApplicationUserDTO {
        return userRepository.getUserByUsername(username)?.toDto()
            ?: throw UsernameNotFoundException("User not found")
    }

    override fun registerUser(registerCommandDTO: RegisterCommandDTO): ApplicationUserDTO {
        val user = mapStudentCommandToUser(registerCommandDTO)

        return userRepository.save(user).toDto()
    }

    override fun deleteUser(username: String) {
        val userToDelete = userRepository.getUserByUsername(username)
            ?: throw UsernameNotFoundException("User not found")

        userRepository.delete(userToDelete)
    }

    override fun assignRoleToUser(username: String, roleName: String): ApplicationUserDTO {
        val user =
            userRepository.getUserByUsername(username)
                ?: throw UsernameNotFoundException("User not found")

        val role: UserRole =
            roleRepository.getRoleByRoleName(roleName)
                ?: throw UserRoleNotFoundException("User role $roleName not found")

        if (user.roles.contains(role)) {
            return user.toDto()
        }

        val updatedUser = user.copy(roles = user.roles.plus(role))

        userRepository.save(updatedUser)

        return updatedUser.toDto()
    }

    override fun updateUser(
        username: String,
        updateApplicationUserCommandDTO: UpdateApplicationUserCommandDTO,
    ): ApplicationUserDTO {
        val existingUser =
            userRepository.getUserByUsername(username)
                ?: throw UsernameNotFoundException("User not found")

        val updatedUser =
            existingUser.copy(
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
