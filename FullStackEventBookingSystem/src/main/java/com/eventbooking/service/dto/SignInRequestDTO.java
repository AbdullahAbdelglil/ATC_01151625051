package com.eventbooking.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SignInRequestDTO {

    @NotBlank(message = "is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "is required")
    private String password;

    public SignInRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
