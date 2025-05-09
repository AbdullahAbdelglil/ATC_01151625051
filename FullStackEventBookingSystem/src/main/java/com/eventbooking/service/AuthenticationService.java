package com.eventbooking.service;

import com.eventbooking.service.dto.JwtAuthenticationResponse;
import com.eventbooking.service.dto.SignInRequestDTO;
import com.eventbooking.service.dto.SignUpRequestDTO;
import com.eventbooking.service.dto.UserDTO;

public interface AuthenticationService {

    UserDTO register(SignUpRequestDTO signUpRequestDTO);

    JwtAuthenticationResponse login(SignInRequestDTO signInRequestDTO);
}
