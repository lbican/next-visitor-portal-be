package com.portal.visitorportal.model.user

import com.portal.visitorportal.model.user.dto.ApplicationUserDTO
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Entity
@Table(schema= "app_users", name = "users")
data class ApplicationUser(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val username: String,
    val email: String,
    @Column(name = "password_hash")
    val password: String,

    val firstName: String,
    val lastName: String,

    @CreatedDate
    val createdAt: LocalDateTime,

    @LastModifiedDate
    val updatedAt: LocalDateTime,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_roles",
        schema = "app_users",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val roles: List<Role>
) {
    constructor() : this(
        id = 0,
        username = "",
        email = "",
        password = "",
        firstName = "",
        lastName = "",
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        roles = emptyList()
    )

    constructor(
        username: String,
        email: String,
        passwordHash: String,
        firstName: String,
        lastName: String
    ) : this(
        id = 0,
        username = username,
        email = email,
        password = passwordHash,
        firstName = firstName,
        lastName = lastName,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        roles = emptyList()
    )

    fun toDto(): ApplicationUserDTO {
        return ApplicationUserDTO(
            id = id,
            username = username,
            email = email,
            firstName = firstName,
            lastName = lastName,
            updatedAt = updatedAt,
            createdAt = createdAt,
            roles = roles.map { it.roleName }
        )
    }
}
