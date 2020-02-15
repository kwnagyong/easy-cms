package com.pky.easycms.service.model

import com.pky.easycms.model.User

data class SignUp(var name: String,
                  var email: String,
                  var password: String) {

    fun toEntity(): User {
         return User(null, name, email, password, null);
    }
}

