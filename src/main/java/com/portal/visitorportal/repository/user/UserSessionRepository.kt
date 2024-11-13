package com.portal.visitorportal.repository.user

import com.portal.visitorportal.model.user.UserSession
import org.springframework.data.repository.CrudRepository

interface UserSessionRepository: CrudRepository<UserSession, Long>
