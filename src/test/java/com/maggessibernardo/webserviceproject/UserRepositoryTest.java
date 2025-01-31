package com.maggessibernardo.webserviceproject;


import static org.junit.jupiter.api.Assertions.*;
import com.maggessibernardo.webserviceproject.entities.User;
import com.maggessibernardo.webserviceproject.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByEmail() {
        User user = new User(null, "Test User", "test@example.com", "123456789", "123456", null);
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByEmail("test@example.com");

        assertTrue(foundUser.isPresent());
        assertEquals("Test User", foundUser.get().getName());
    }
}
