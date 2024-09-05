package com.portal.visitorportal.service.user

import com.portal.visitorportal.model.user.User
import com.portal.visitorportal.model.user.UserCommand
import com.portal.visitorportal.model.user.UserDTO
import com.portal.visitorportal.repository.user.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
): UserService {
    override fun getUserByUsername(username: String): UserDTO {
        return userRepository.getUserByUsername(username).toDto()
    }

    override fun registerUser(userCommand: UserCommand): UserDTO {
        val user = mapStudentCommandToUser(userCommand)

        return userRepository.save(user).toDto()
    }

    private fun mapStudentCommandToUser(userCommand: UserCommand): User {
        val passwordHash = bCryptPasswordEncoder.encode(userCommand.password)
        val user = User(
            email = userCommand.email,
            username = userCommand.username,
            passwordHash = passwordHash,
            firstName = userCommand.firstName,
            lastName = userCommand.lastName
        )

        return user;
    }
}