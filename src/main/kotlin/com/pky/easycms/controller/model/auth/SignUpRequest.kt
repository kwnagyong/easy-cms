package com.pky.easycms.controller.model.auth

import com.pky.easycms.service.model.SignUp
import javax.validation.constraints.Pattern

data class SignUpRequest(val name:String,
                         @get:Pattern(regexp = "^(.+)@(.+)\$")
                         val email:String,
                         val password: String) {

    fun toSignUp(): SignUp {
        return SignUp(name,
                email,
                password);
    }
}