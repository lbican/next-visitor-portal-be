package com.portal.visitorportal.service.user

import com.portal.visitorportal.model.user.dto.UserSignUpCommandDTO
import com.portal.visitorportal.model.user.dto.ApplicationUserDTO

interface UserService {
    fun getUserByUsername(username: String): ApplicationUserDTO
    fun registerUser(userSignUpCommandDTO: UserSignUpCommandDTO): ApplicationUserDTO
}