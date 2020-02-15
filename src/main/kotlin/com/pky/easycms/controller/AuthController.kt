package com.pky.easycms.controller

import com.pky.easycms.controller.model.auth.SignInRequest
import com.pky.easycms.controller.model.auth.SignInResponse
import com.pky.easycms.controller.model.auth.SignUpRequest
import com.pky.easycms.controller.model.auth.SignUpResponse
import com.pky.easycms.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("auth")
class AuthController(@Autowired private val userService: UserService) {

    @PostMapping("sign-up")
    fun signUp(@RequestBody @Valid request: SignUpRequest): SignUpResponse {
        return SignUpResponse.fromSignResult(userService.signUp(request.toSignUp()));
    }

    @PostMapping("/sign-in")
    fun signIn(@RequestBody request: SignInRequest): SignInResponse? {
        return null;
    }

}