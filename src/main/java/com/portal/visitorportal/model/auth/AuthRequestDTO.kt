package com.portal.visitorportal.model.auth

import jakarta.validation.constraints.NotBlank

data class AuthRequestDTO(
    @field:NotBlank val username: String,
    @field:NotBlank val password: String,
)
