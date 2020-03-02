package com.pky.easycms.configuration.security.jwt

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class PreJwtAuthenticationToken(token: String) : UsernamePasswordAuthenticationToken(token, token.length)