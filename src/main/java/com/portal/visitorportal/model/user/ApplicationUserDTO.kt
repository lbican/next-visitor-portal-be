package com.portal.visitorportal.model.user

import java.time.LocalDateTime

data class ApplicationUserDTO(
    val id: Long,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val updatedAt: LocalDateTime,
    val createdAt: LocalDateTime,
    val roles: List<String>,
    val profilePicture: String?,
    val website: String?,
    val bio: String?,
    val location: String?,
    val phoneNumber: String?,
)
