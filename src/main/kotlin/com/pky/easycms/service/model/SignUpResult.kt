package com.pky.easycms.service.model

import com.pky.easycms.model.User
import java.util.*

data class SignUpResult(var userNo: Int,
                   var email: String,
                   var cratedAt: Date) {

    companion object {
        fun fromEntity(user: User): SignUpResult {
            return SignUpResult(user.userNo?:0, user.email, user.createdAt?: Date())
        }
    }
}