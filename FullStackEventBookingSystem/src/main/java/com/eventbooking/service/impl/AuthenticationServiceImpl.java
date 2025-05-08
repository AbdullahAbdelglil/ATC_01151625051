package com.eventbooking.service.impl;

import com.eventbooking.service.AuthenticationService;
import com.eventbooking.service.EventService;
import com.eventbooking.service.UserService;
import com.eventbooking.service.dto.SignUpRequestDTO;
import com.eventbooking.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final static Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(UserService userService,
                                     PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO signUp(SignUpRequestDTO signUpRequestDTO) {
        log.debug("signing Up new User UserRequestDTO {}", signUpRequestDTO);

        // Hash the password first
        signUpRequestDTO.setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()));
        UserDTO userDTO = new UserDTO(signUpRequestDTO);
        return userService.save(userDTO);
    }
}
