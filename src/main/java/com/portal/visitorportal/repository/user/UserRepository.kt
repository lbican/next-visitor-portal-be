package com.portal.visitorportal.repository.user

import com.portal.visitorportal.model.user.ApplicationUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<ApplicationUser, Long> {
    fun getUserByUsername(username: String?): ApplicationUser?
    fun save(applicationUser: ApplicationUser): ApplicationUser
}