package com.pky.easycms.controller

import com.pky.easycms.controller.model.auth.AddOrganizationRequest
import com.pky.easycms.controller.model.auth.SignUpRequest
import com.pky.easycms.controller.model.auth.SignUpResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: SignUpRequest): SignUpResponse? {
        return null;
    }

}