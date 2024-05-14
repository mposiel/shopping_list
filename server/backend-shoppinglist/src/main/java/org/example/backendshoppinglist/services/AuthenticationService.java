package org.example.backendshoppinglist.services;

import org.example.backendshoppinglist.dtos.LoginUserDto;
import org.example.backendshoppinglist.dtos.RegisterUserDto;
import org.example.backendshoppinglist.entities.User;
import org.example.backendshoppinglist.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticaationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticaationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticaationManager = authenticaationManager;
    }

    public User signup(RegisterUserDto registerUserDto) {
        User user = new User(
                registerUserDto.getFullName(),
                registerUserDto.getEmail(),
                passwordEncoder.encode(registerUserDto.getPassword())
        );
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto loginUserDto) {
        authenticaationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.getEmail(),
                        loginUserDto.getPassword()
                )
        );
        return userRepository.findByEmail(
                loginUserDto.getEmail()
        ).orElseThrow();
    }
}
