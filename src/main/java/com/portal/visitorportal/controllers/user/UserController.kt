package com.portal.visitorportal.controllers.user

import com.portal.visitorportal.model.user.UserCommand
import com.portal.visitorportal.model.user.UserDTO
import com.portal.visitorportal.service.user.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/user/{username}")
    fun getUserByUsername(@PathVariable username: String): UserDTO {
        return userService.getUserByUsername(username)
    }

    @PostMapping("/register")
    fun registerUser(@Valid @RequestBody userCommand: UserCommand): UserDTO {
        return userService.registerUser(userCommand)
    }
}
