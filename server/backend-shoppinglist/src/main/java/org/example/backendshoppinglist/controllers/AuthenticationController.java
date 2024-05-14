package org.example.backendshoppinglist.controllers;

import org.example.backendshoppinglist.dtos.LoginUserDto;
import org.example.backendshoppinglist.dtos.RegisterUserDto;
import org.example.backendshoppinglist.entities.User;
import org.example.backendshoppinglist.responses.LoginResponse;
import org.example.backendshoppinglist.services.AuthenticationService;
import org.example.backendshoppinglist.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@Controller
public class AuthenticationController {
    private AuthenticationService authenticationService;

    private JwtService jwtService;

    public AuthenticationController(AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody RegisterUserDto registerUserDto) {
        User user = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String token = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse(
                token,
                jwtService.getExpirationTime()
        );
        return ResponseEntity.ok(loginResponse);
    }

}
