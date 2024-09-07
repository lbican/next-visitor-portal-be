package com.portal.visitorportal.service.user

import com.portal.visitorportal.model.user.RegisterCommandDTO
import com.portal.visitorportal.model.user.ApplicationUserDTO

interface UserService {
    fun getUserByUsername(username: String): ApplicationUserDTO
    fun registerUser(registerCommandDTO: RegisterCommandDTO): ApplicationUserDTO
}