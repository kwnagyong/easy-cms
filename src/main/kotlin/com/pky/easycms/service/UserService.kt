package com.pky.easycms.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.pky.easycms.UserRepository
import com.pky.easycms.error.InvalidJwtException
import com.pky.easycms.service.model.SignIn
import com.pky.easycms.service.model.SignUp
import com.pky.easycms.service.model.UserResult
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository,
                  private val passwordEncoder: PasswordEncoder,
                  @Value("\${jwt.secret}") private val jwtSecret: String = "") : UserDetailsService {

    val algorithm: Algorithm = Algorithm.HMAC256(jwtSecret);
    val jwtVerifier = JWT.require(algorithm).build();

    fun signUp(signUp: SignUp): String {
        signUp.password = passwordEncoder.encode(signUp.password)
        return createJwt(UserResult.fromEntity(userRepository.save(signUp.toEntity())))
    }

    fun signIn(signIn: SignIn): String {
        val user = userRepository.findByEmail(signIn.email) ?: throw NoSuchElementException()
        if (!passwordEncoder.matches(signIn.password, user.password)) {
            throw NoSuchElementException()
        }
        return createJwt(UserResult.fromEntity(user));
    }

    override fun loadUserByUsername(username: String): User {
        val user = userRepository.findByEmail(username)

        return User(user.email, user.password, Arrays.asList())
    }

    fun createJwt(userResult: UserResult): String {
        return JWT.create()
                .withClaim("userNo", userResult.userNo)
                .withClaim("email", userResult.email)
                .withClaim("name", userResult.name)
                .sign(algorithm);
    }

    fun getUserFromJwt(jwtToken: String): UserResult {
        val decoder = jwtVerifier.verify(jwtToken) ?: throw InvalidJwtException(jwtToken)

        return UserResult(decoder.getClaim("userNo").asInt(), decoder.getClaim("email").asString(), decoder.getClaim("name").asString())
    }

}