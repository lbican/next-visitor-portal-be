package com.portal.visitorportal.model.user

import jakarta.persistence.*

@Entity
@Table(schema = "app_users", name = "user_profiles")
data class UserProfile(

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    val applicationUser: ApplicationUser,
    val bio: String,
    val profilePicture: String,
    val website: String,
    val location: String,
    val phoneNumber: String
) {
    constructor() : this(
        applicationUser = ApplicationUser(),
        bio = "",
        profilePicture = "",
        website = "",
        location = "",
        phoneNumber = ""
    )
}
