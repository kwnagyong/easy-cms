package com.pky.easycms.service

import com.pky.easycms.UserRepository
import com.pky.easycms.service.model.SignUp
import com.pky.easycms.service.model.SignUpResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService(@Autowired private val userRepository: UserRepository) {

    fun signUp(signUp: SignUp): SignUpResult {
        return SignUpResult.fromEntity(userRepository.save(signUp.toEntity()))
    }
}