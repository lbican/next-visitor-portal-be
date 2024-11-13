package com.portal.visitorportal.user

import com.portal.visitorportal.user.model.UserSession
import org.springframework.data.repository.CrudRepository

interface UserSessionRepository : CrudRepository<UserSession, Long>
