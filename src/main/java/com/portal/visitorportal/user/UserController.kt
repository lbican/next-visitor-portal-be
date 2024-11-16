package com.portal.visitorportal.user

import com.portal.visitorportal.user.model.ApplicationUserDTO
import com.portal.visitorportal.user.model.commands.RegisterCommandDTO
import com.portal.visitorportal.user.model.commands.UpdateApplicationUserCommandDTO
import jakarta.validation.Valid
import org.springframework.http.MediaType
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

    @PutMapping("/{username}/role", consumes = [MediaType.TEXT_PLAIN_VALUE])
    fun updateRole(@PathVariable username: String, @RequestBody roleName: String): ApplicationUserDTO {
        return userService.assignRoleToUser(username, roleName)
    }

    @DeleteMapping("/{username}")
    fun deleteUser(@PathVariable username: String) {
        userService.deleteUser(username)
    }

    @PostMapping("/register")
    fun registerUser(
        @Valid @RequestBody registerCommandDTO: RegisterCommandDTO
    ): ApplicationUserDTO {
        return userService.registerUser(registerCommandDTO)
    }
}
