package com.portal.visitorportal.model.user.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class UserSignInCommandDTO(
    @field:NotBlank
    val username: String,

    @field:Email
    @field:NotBlank
    val email: String,

    @field:NotBlank
    val password: String
)
