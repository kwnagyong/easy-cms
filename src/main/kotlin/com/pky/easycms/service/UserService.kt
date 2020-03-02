package com.pky.easycms.service

import com.pky.easycms.UserRepository
import com.pky.easycms.service.model.SignIn
import com.pky.easycms.service.model.SignUp
import com.pky.easycms.service.model.UserResult
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository,
                  private val passwordEncoder: PasswordEncoder,
                  private val jwtService: JwtService) : UserDetailsService {


    fun signUp(signUp: SignUp): String {
        signUp.password = passwordEncoder.encode(signUp.password)
        return jwtService.createJwt(UserResult.fromEntity(userRepository.save(signUp.toEntity())))
    }

    fun signIn(signIn: SignIn): String {
        val user = userRepository.findByEmail(signIn.email)
        if (!passwordEncoder.matches(signIn.password, user.password)) {
            throw NoSuchElementException()
        }
        return jwtService.createJwt(UserResult.fromEntity(user))
    }

    override fun loadUserByUsername(username: String): User {
        val user = userRepository.findByEmail(username)

        return User(user.email, user.password, Arrays.asList())
    }

}