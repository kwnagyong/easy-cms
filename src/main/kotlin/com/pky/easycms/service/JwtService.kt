package com.pky.easycms.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.pky.easycms.error.InvalidJwtException
import com.pky.easycms.service.model.UserResult
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class JwtService(@Value("\${jwt.secret}") private val jwtSecret: String = "") {
    val algorithm: Algorithm = Algorithm.HMAC256(jwtSecret)
    val jwtVerifier = JWT.require(algorithm).build()
    val USER_NO = "userNo"
    val EMAIL = "email"
    val NAME = "name"

    fun createJwt(userResult: UserResult): String {
        return JWT.create()
                .withClaim(USER_NO, userResult.userNo)
                .withClaim(EMAIL, userResult.email)
                .withClaim(NAME, userResult.name)
                .sign(algorithm)
    }

    fun userResultFromJwt(jwtToken: String): UserResult {
        val decoder = jwtVerifier.verify(jwtToken) ?: throw InvalidJwtException(jwtToken)

        return UserResult(decoder.getClaim(USER_NO).asInt(), decoder.getClaim(EMAIL).asString(), decoder.getClaim(NAME).asString())
    }

}