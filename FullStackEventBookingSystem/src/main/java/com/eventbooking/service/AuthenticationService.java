package com.eventbooking.service;

import com.eventbooking.service.dto.SignUpRequestDTO;
import com.eventbooking.service.dto.UserDTO;

public interface AuthenticationService {

    UserDTO signUp(SignUpRequestDTO signUpRequestDTO);
}
