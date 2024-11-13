package com.portal.visitorportal.user

import com.portal.visitorportal.user.model.ApplicationUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<ApplicationUser, Long> {
    fun getUserByUsername(username: String?): ApplicationUser?

    fun save(applicationUser: ApplicationUser): ApplicationUser
}
