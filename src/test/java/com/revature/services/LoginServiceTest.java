package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.models.User;
import com.revature.repositories.UserRepository;
import java.util.Optional;

@SpringBootTest
public class LoginServiceTest {

    @Autowired
	private static UserService service;

    @Autowired
    private static UserRepository repository;

    @Autowired
    private static AuthService authService;

    @BeforeAll
    public static void setUserService() {
        authService = new AuthService(service);
        service = new UserService(repository);
        
    }

    // @Test
    // public void loginSuccessTest() {
    //     String email = "joewham@gmail.com";
    //     String password = "123";
    //     Optional<User> actual = service.findByCredentials(email, password);
    //     assertEquals(null, actual);
    // }

//    @Test
//    public void getUserByEmailTest() {
//        Optional<?> actual = service.findByEmail("joewham14@gmail.com");
//        User optExpected = new User(19,"joewham14@gmail.com","Beachbum2018!", "Joey", "Wham", "What is the name of your favorite pet?", "Bear");
//        assertEquals(optExpected, actual);
//        actual.of(optExpected.getFirstName());
//    }
}
