package com.pky.easycms.configuration.security.jwt

import com.pky.easycms.service.model.UserResult
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class PostJwtAuthenticationToken(userResult: UserResult) : UsernamePasswordAuthenticationToken(userResult, userResult.userNo, emptyList())