package com.pky.easycms.service

import com.pky.easycms.UserRepository
import com.pky.easycms.model.User
import com.pky.easycms.service.model.SignUp

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

class UserServiceTest {

    @Mock
    private lateinit var userRepository: UserRepository;

    @Mock
    private lateinit var passwordEncoder: PasswordEncoder;

    private var jwtService: JwtService = JwtService()

    private lateinit var userService: UserService

    @BeforeEach
    internal fun setUp() {
        MockitoAnnotations.initMocks(this);
        userService = UserService(userRepository, passwordEncoder, jwtService)
    }

    @Test fun `should add user`() {
        val name = "email"
        val email = "email"
        val password = "password"
        val createdAt = Date()
        val userNo = 1

        var signUp = SignUp(name, email, password)

        Mockito.`when`(passwordEncoder.encode(password)).thenReturn(password)
        Mockito.`when`(userRepository.save(signUp.toEntity())).thenReturn(User(userNo, name, email, password, createdAt))
        val jwt = userService.signUp(signUp)
        val userResult = jwtService.userResultFromJwt(jwt)

        assertNotNull(userResult)
        assertEquals(userNo, userResult.userNo)
        assertEquals(email, userResult.email)
        assertEquals(name, userResult.name)
    }
}