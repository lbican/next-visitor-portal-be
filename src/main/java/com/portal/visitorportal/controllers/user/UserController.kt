package com.portal.visitorportal.controllers.user

import com.portal.visitorportal.model.user.ApplicationUserDTO
import com.portal.visitorportal.model.user.commands.RegisterCommandDTO
import com.portal.visitorportal.model.user.commands.UpdateApplicationUserCommandDTO
import com.portal.visitorportal.service.user.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/public")
class UserController(private val userService: UserService) {

    @GetMapping("/{username}")
    fun fetchUser(@PathVariable username: String): ApplicationUserDTO {
        return userService.fetchUser(username)
    }

    @PutMapping("/{username}")
    fun updateUser(
        @PathVariable username: String,
        @Valid @RequestBody updateApplicationUserCommandDTO: UpdateApplicationUserCommandDTO,
    ): ApplicationUserDTO {
        return userService.updateUser(username, updateApplicationUserCommandDTO)
    }

    @PostMapping("/register")
    fun registerUser(
        @Valid @RequestBody registerCommandDTO: RegisterCommandDTO
    ): ApplicationUserDTO {
        return userService.registerUser(registerCommandDTO)
    }
}
