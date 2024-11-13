package com.portal.visitorportal.service.user

import com.portal.visitorportal.model.user.ApplicationUserDTO
import com.portal.visitorportal.model.user.commands.RegisterCommandDTO
import com.portal.visitorportal.model.user.commands.UpdateApplicationUserCommandDTO
import org.springframework.security.access.prepost.PreAuthorize

interface UserService {
    @PreAuthorize("#username == authentication.name")
    fun fetchUser(username: String): ApplicationUserDTO

    fun updateUser(
        username: String,
        updateApplicationUserCommandDTO: UpdateApplicationUserCommandDTO,
    ): ApplicationUserDTO

    fun registerUser(registerCommandDTO: RegisterCommandDTO): ApplicationUserDTO
}
