package com.portal.visitorportal.model.user.commands

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class UpdateApplicationUserCommandDTO(
    @field:Email @field:NotBlank val email: String,
    @field:NotBlank val firstName: String,
    @field:NotBlank val lastName: String,
    val bio: String?,
    val profilePicture: String?,
    val website: String?,
    val location: String?,
    val phoneNumber: String?
)
