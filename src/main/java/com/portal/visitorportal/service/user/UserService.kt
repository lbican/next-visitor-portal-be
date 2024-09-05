package com.portal.visitorportal.service.user

import com.portal.visitorportal.model.user.UserCommand
import com.portal.visitorportal.model.user.UserDTO

interface UserService {
    fun getUserByUsername(username: String): UserDTO
    fun registerUser(userCommand: UserCommand): UserDTO
}