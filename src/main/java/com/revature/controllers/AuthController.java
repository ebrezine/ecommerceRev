package com.revature.controllers;

import com.revature.annotations.Authorized;
import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;



@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        Optional<User> optional = authService.findByCredentials(loginRequest.getEmail(), loginRequest.getPassword());

        if(!optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        session.setAttribute("user", optional.get());

        return ResponseEntity.ok(optional.get());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute("user");

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) {
        User created = new User(0,
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getQuestion(),
                registerRequest.getAnswer());

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(created));
    }

    
    @PatchMapping("/resetPassword")
    public ResponseEntity<User> change(@RequestBody LoginRequest reset) {
        
        String email = reset.getEmail();
        Optional<User> optional = userService.findByEmail(email);
       
        String newPassword = reset.getPassword();
        optional.get().setPassword(newPassword);
        User user = new User();
        user = optional.get();
       
       return ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
            
            Optional<User> optional = userService.findByEmail(email);
            if(!optional.isPresent()) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(optional.get());
    }

}
