package com.portal.visitorportal.model.user

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import java.sql.Timestamp
import java.time.LocalDateTime

@Entity
@Table(name = "user_sessions")
data class UserSession(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    val sessionToken: String,

    @CreatedDate
    val createdAt: LocalDateTime,
    val expiresAt: LocalDateTime,
    val lastAccessed: LocalDateTime,
) {
    constructor() : this(
        id = 0,
        user = User(),
        sessionToken = "",
        createdAt = LocalDateTime.now(),
        expiresAt = LocalDateTime.now(),
        lastAccessed = LocalDateTime.now()
    )
}
