package com.pky.easycms.service

import com.pky.easycms.UserRepository
import com.pky.easycms.service.model.SignUp
import com.pky.easycms.service.model.SignUpResult
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository,
                  private val passwordEncoder: PasswordEncoder) : UserDetailsService {

    fun signUp(signUp: SignUp): SignUpResult {
        signUp.password = passwordEncoder.encode(signUp.password);
        return SignUpResult.fromEntity(userRepository.save(signUp.toEntity()))
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByEmail(username)

        return User(user.email, user.password, Arrays.asList());
    }
}