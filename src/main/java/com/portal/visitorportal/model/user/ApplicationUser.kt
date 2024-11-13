package com.portal.visitorportal.model.user

import jakarta.persistence.*
import java.time.LocalDateTime
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate

@Entity
@Table(schema = "app_users", name = "users")
data class ApplicationUser(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    val username: String,
    val email: String,
    @Column(name = "password_hash") val password: String,
    val firstName: String,
    val lastName: String,
    @CreatedDate val createdAt: LocalDateTime,
    @LastModifiedDate val updatedAt: LocalDateTime,
    val profilePicture: String?,
    val website: String?,
    val bio: String?,
    val location: String?,
    val phoneNumber: String?,
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        schema = "app_users",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")],
    )
    val roles: List<Role>,
) {
    constructor() :
        this(
            id = 0,
            username = "",
            email = "",
            password = "",
            firstName = "",
            lastName = "",
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            profilePicture = "",
            website = "",
            bio = "",
            location = "",
            phoneNumber = "",
            roles = emptyList(),
        )

    constructor(
        username: String,
        email: String,
        passwordHash: String,
        firstName: String,
        lastName: String,
    ) : this(
        id = 0,
        username = username,
        email = email,
        password = passwordHash,
        firstName = firstName,
        lastName = lastName,
        profilePicture = "",
        website = "",
        bio = "",
        location = "",
        phoneNumber = "",
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        roles = emptyList(),
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
            roles = roles.map { it.roleName },
            profilePicture = profilePicture,
            website = website,
            bio = bio,
            location = location,
            phoneNumber = phoneNumber,
        )
    }
}
