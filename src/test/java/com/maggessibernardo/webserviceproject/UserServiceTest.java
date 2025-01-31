package com.maggessibernardo.webserviceproject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.maggessibernardo.webserviceproject.entities.User;
import com.maggessibernardo.webserviceproject.repositories.UserRepository;
import com.maggessibernardo.webserviceproject.services.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        User user = new User(1L, "John Doe", "john@example.com", "988888888", "123456", null);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.findById(1L);

        assertNotNull(foundUser);
        assertEquals("John Doe", foundUser.getName());
    }
}
