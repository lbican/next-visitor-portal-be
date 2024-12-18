package com.portal.visitorportal.user.model

import jakarta.persistence.*
import lombok.AllArgsConstructor

@Entity
@Table(schema = "app_users", name = "roles")
@AllArgsConstructor
data class UserRole(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    val roleName: String,
) {
    constructor() : this(id = 0, roleName = "")
}
