package com.portal.visitorportal.user

import com.portal.visitorportal.user.model.UserRole
import org.springframework.data.repository.CrudRepository

interface UserRoleRepository : CrudRepository<UserRole, Long> {
    fun getRoleByRoleName(roleName: String): UserRole?
}
