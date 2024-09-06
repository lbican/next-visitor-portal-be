package com.portal.visitorportal.service.user

import com.portal.visitorportal.model.user.ApplicationUser
import com.portal.visitorportal.model.user.dto.UserSignUpCommandDTO
import com.portal.visitorportal.model.user.dto.ApplicationUserDTO
import com.portal.visitorportal.repository.user.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
): UserService {
    override fun getUserByUsername(username: String): ApplicationUserDTO {
        return userRepository.getUserByUsername(username)?.toDto() ?: throw UsernameNotFoundException("User not found")
    }

    override fun registerUser(userSignUpCommandDTO: UserSignUpCommandDTO): ApplicationUserDTO {
        val user = mapStudentCommandToUser(userSignUpCommandDTO)

        return userRepository.save(user).toDto()
    }

    private fun mapStudentCommandToUser(userSignUpCommandDTO: UserSignUpCommandDTO): ApplicationUser {
        val passwordHash = bCryptPasswordEncoder.encode(userSignUpCommandDTO.password)
        val applicationUser = ApplicationUser(
            email = userSignUpCommandDTO.email,
            username = userSignUpCommandDTO.username,
            passwordHash = passwordHash,
            firstName = userSignUpCommandDTO.firstName,
            lastName = userSignUpCommandDTO.lastName
        )

        return applicationUser;
    }
}