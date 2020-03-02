package com.pky.easycms.configuration.security.provider

import com.pky.easycms.configuration.security.jwt.PostJwtAuthenticationToken
import com.pky.easycms.configuration.security.jwt.PreJwtAuthenticationToken
import com.pky.easycms.service.JwtService
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class JwtAuthenticationProvider(private val jwtService: JwtService) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication): Authentication {
        return PostJwtAuthenticationToken(jwtService.userResultFromJwt(authentication.principal as String))
    }

    override fun supports(aClass: Class<*>?): Boolean {
        return PreJwtAuthenticationToken::class.java.isAssignableFrom(aClass)
    }
}