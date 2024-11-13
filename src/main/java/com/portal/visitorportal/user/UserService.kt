package com.portal.visitorportal.user

import com.portal.visitorportal.user.model.ApplicationUserDTO
import com.portal.visitorportal.user.model.commands.RegisterCommandDTO
import com.portal.visitorportal.user.model.commands.UpdateApplicationUserCommandDTO
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
