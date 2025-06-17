package com.test.security.controller;

import com.test.security.controller.dto.JwtResponseDTO;
import com.test.security.controller.dto.LoginRequestDTO;
import com.test.security.controller.dto.UserDTO;
import com.test.security.domain.entity.User;
import com.test.security.service.UserService;
import com.test.security.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        try {
            User newUser = userService.registerUser(userDTO);
            return ResponseEntity.ok("User registered successfully! ID: " + newUser.getId());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        // Authenticate the user with the provided email and password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha())
        );

        // If authentication is successful, generate a JWT.
        User user = (User) authentication.getPrincipal();
        String jwt = jwtService.generateToken(user);

        // Return the JWT in the response.
        return ResponseEntity.ok(new JwtResponseDTO(jwt));
    }
}
