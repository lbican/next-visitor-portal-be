package com.portal.visitorportal.controllers.user

import com.portal.visitorportal.model.user.dto.UserSignUpCommandDTO
import com.portal.visitorportal.model.user.dto.ApplicationUserDTO
import com.portal.visitorportal.service.user.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/public")
class UserController(
    private val userService: UserService
) {

    @GetMapping("/{username}")
    fun getUserByUsername(@PathVariable username: String): ApplicationUserDTO {
        return userService.getUserByUsername(username)
    }

    @PostMapping("/register")
    fun registerUser(@Valid @RequestBody userSignUpCommandDTO: UserSignUpCommandDTO): ApplicationUserDTO {
        return userService.registerUser(userSignUpCommandDTO)
    }
}
