package com.portal.visitorportal.user.model

import jakarta.persistence.*
import java.time.LocalDateTime
import org.springframework.data.annotation.CreatedDate

@Entity
@Table(schema = "app_users", name = "user_sessions")
data class UserSession(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val applicationUser: ApplicationUser,
    val sessionToken: String,
    val expiresAt: LocalDateTime,
    val lastAccessed: LocalDateTime,
    @CreatedDate val createdAt: LocalDateTime,
) {
    constructor() :
        this(
            id = 0,
            applicationUser = ApplicationUser(),
            sessionToken = "",
            createdAt = LocalDateTime.now(),
            expiresAt = LocalDateTime.now(),
            lastAccessed = LocalDateTime.now(),
        )
}
