package com.eventbooking.service.impl;

import com.eventbooking.service.AuthenticationService;
import com.eventbooking.service.JWTService;
import com.eventbooking.service.UserService;
import com.eventbooking.service.dto.JwtAuthenticationResponse;
import com.eventbooking.service.dto.SignInRequestDTO;
import com.eventbooking.service.dto.SignUpRequestDTO;
import com.eventbooking.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final static Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthenticationServiceImpl(UserService userService,
                                     PasswordEncoder passwordEncoder,
                                     AuthenticationManager authenticationManager,
                                     JWTService jwtService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public UserDTO register(SignUpRequestDTO signUpRequestDTO) {
        log.debug("signing Up new User UserRequestDTO {}", signUpRequestDTO);

        // Hash the password first
        signUpRequestDTO.setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()));
        UserDTO userDTO = new UserDTO(signUpRequestDTO);
        return userService.save(userDTO);
    }

    @Override
    public JwtAuthenticationResponse login(SignInRequestDTO signInRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequestDTO.getEmail(),
                        signInRequestDTO.getPassword())
        );

        UserDTO user = userService.findOne(signInRequestDTO.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("Invalid email or password"));

        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse response = new JwtAuthenticationResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        return response;
    }
}
