package com.pky.easycms.configuration.security.filter

import com.pky.easycms.configuration.security.jwt.PreJwtAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.RequestMatcher
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(match: RequestMatcher) : AbstractAuthenticationProcessingFilter(match) {
    val HEADER_VALUE_PREFIX = "Bearer "
    val HEADER_KEY = "Authorization"

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {

        val token: String = request.getHeader(HEADER_KEY).substringAfter(HEADER_VALUE_PREFIX)

        return super.getAuthenticationManager().authenticate(PreJwtAuthenticationToken(token))
    }

    override fun unsuccessfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, failed: AuthenticationException) {
        SecurityContextHolder.clearContext()
        super.unsuccessfulAuthentication(request, response, failed)
    }

    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain, authResult: Authentication) {
        val context = SecurityContextHolder.createEmptyContext()

        context.authentication = authResult
        SecurityContextHolder.setContext(context)

        chain.doFilter(request, response)
    }
}