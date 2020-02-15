package com.pky.easycms.service

import com.pky.easycms.UserRepository
import com.pky.easycms.model.User
import com.pky.easycms.service.model.SignUp

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*

class UserServiceTest {

    @Mock
    private lateinit var userRepository: UserRepository;

    @InjectMocks
    private lateinit var userService: UserService;

    @BeforeEach
    internal fun setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test fun `should add user`() {
        val name = "email"
        val email = "email"
        val password = "password"
        val createdAt = Date()
        val userNo = 1

        var signUp = SignUp(name, email, password);

        Mockito.`when`(userRepository.save(signUp.toEntity())).thenReturn(User(userNo, name, email, password,  createdAt));
        val result = userService.signUp(signUp);

        assertEquals(userNo, result.userNo);
        assertEquals(email, result.email);
        assertEquals(createdAt, result.cratedAt);
    }
}