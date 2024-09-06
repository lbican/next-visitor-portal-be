package com.portal.visitorportal.model.user.dto
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class UserSignUpCommandDTO(
    @field:NotBlank
    val username: String,

    @field:Email
    @field:NotBlank
    val email: String,

    @field:NotBlank
    val firstName: String,

    @field:NotBlank
    val lastName: String,

    @field:NotBlank(message = "Password must not be empty")
    @field:Size(min = 8, message = "Password must be at least 8 characters long")
    @field:Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit")
    val password: String
)
