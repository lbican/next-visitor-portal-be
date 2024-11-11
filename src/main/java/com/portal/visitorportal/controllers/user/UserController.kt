package com.portal.visitorportal.controllers.user

import com.portal.visitorportal.model.user.ApplicationUserDTO
import com.portal.visitorportal.model.user.RegisterCommandDTO
import com.portal.visitorportal.service.user.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/public")
class UserController(private val userService: UserService) {

    @GetMapping("/{username}")
    fun getUserByUsername(@PathVariable username: String): ApplicationUserDTO {
        return userService.getUserByUsername(username)
    }

    @PostMapping("/register")
    fun registerUser(
        @Valid @RequestBody registerCommandDTO: RegisterCommandDTO
    ): ApplicationUserDTO {
        return userService.registerUser(registerCommandDTO)
    }
}
