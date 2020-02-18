package com.pky.easycms.service.model

import com.pky.easycms.model.User

data class UserResult(var userNo: Int,
                      var email: String,
                      var name: String) {

    companion object {
        fun fromEntity(user: User): UserResult {
            return UserResult(user.userNo ?: 0, user.email, user.name)
        }
    }
}