package com.portal.visitorportal.model.user

import jakarta.persistence.*

@Entity
@Table(name = "user_roles")
data class UserRole(

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    val role: Role
) {
    constructor() : this(
        user = User(),
        role = Role()
    )
}
