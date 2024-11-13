package com.portal.visitorportal.user.model

import com.portal.visitorportal.user.model.commands.RegisterCommandDTO
import com.portal.visitorportal.user.model.commands.UpdateApplicationUserCommandDTO
import com.portal.visitorportal.user.UserService
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
