package com.eventbooking.web.rest;

import com.eventbooking.service.AuthenticationService;
import com.eventbooking.service.dto.SignUpRequestDTO;
import com.eventbooking.service.dto.UserDTO;
import com.eventbooking.web.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        UserDTO userDTO = authenticationService.signUp(signUpRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }
}
