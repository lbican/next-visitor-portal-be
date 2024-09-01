package com.portal.visitorportal.model.user

import jakarta.persistence.*
import lombok.AllArgsConstructor
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.sql.Timestamp
import java.time.LocalDateTime

@Entity
@Table(name = "users")
@AllArgsConstructor
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val username: String,
    val email: String,
    val passwordHash: String,
    val firstName: String,
    val lastName: String,

    @CreatedDate
    val createdAt: LocalDateTime,

    @LastModifiedDate
    val updatedAt: LocalDateTime
) {
    constructor() : this(
        id = 0,
        username = "",
        email = "",
        passwordHash = "",
        firstName = "",
        lastName = "",
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now()
    )
}
