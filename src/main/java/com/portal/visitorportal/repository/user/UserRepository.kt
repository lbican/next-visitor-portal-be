package com.portal.visitorportal.repository.user

import com.portal.visitorportal.model.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun getUserByUsername(username: String): User
    fun save(user: User): User
}