package com.pky.easycms.repository

import com.pky.easycms.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Integer> {
    fun findByEmail(username: String): User

}