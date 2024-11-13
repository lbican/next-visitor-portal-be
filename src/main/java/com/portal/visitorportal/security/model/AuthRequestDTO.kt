package com.portal.visitorportal.security.model

import jakarta.validation.constraints.NotBlank

data class AuthRequestDTO(
    @field:NotBlank val username: String,
    @field:NotBlank val password: String,
)
