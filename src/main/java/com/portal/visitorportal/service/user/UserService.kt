package com.portal.visitorportal.service.user

import com.portal.visitorportal.model.user.ApplicationUserDTO
import com.portal.visitorportal.model.user.RegisterCommandDTO
import org.springframework.security.access.prepost.PreAuthorize

interface UserService {
    @PreAuthorize("#username == authentication.name")
    fun getUserByUsername(username: String): ApplicationUserDTO

    fun registerUser(registerCommandDTO: RegisterCommandDTO): ApplicationUserDTO
}
