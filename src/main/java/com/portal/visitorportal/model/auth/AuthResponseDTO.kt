package com.portal.visitorportal.model.auth

data class AuthResponseDTO(
    val accessToken: String,
    val refreshToken: String
)
