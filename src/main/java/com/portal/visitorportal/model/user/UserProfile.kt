package com.portal.visitorportal.model.user

import jakarta.persistence.*

@Entity
@Table(schema = "app_users", name = "user_profiles")
data class UserProfile(

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    val user: User,
    val bio: String,
    val profilePicture: String,
    val website: String,
    val location: String,
    val phoneNumber: String
) {
    constructor() : this(
        user = User(),
        bio = "",
        profilePicture = "",
        website = "",
        location = "",
        phoneNumber = ""
    )
}
