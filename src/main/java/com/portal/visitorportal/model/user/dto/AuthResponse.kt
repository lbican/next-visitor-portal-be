package com.portal.visitorportal.model.user.dto

data class AuthResponse(
    val accessToken: String,
    val refreshToken: String
)
