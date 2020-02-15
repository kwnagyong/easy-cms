package com.pky.easycms.controller.model.auth

import com.pky.easycms.service.model.SignUpResult

data class SignUpResponse(var userNo: Int,
                          var name: String) {
    companion object {
        fun fromSignResult(signUpResult: SignUpResult): SignUpResponse {
            return SignUpResponse(signUpResult.userNo, signUpResult.email)
        }
    }
}