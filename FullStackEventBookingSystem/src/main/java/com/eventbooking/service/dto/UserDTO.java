package com.eventbooking.service.dto;

import com.eventbooking.domain.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @JsonIgnore
    private String password;

    private Role role;

    public UserDTO() {
    }

    public UserDTO(Long id,
                   String firstName,
                   String lastName,
                   String email,
                   String password,
                   Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserDTO(SignUpRequestDTO signUpRequestDTO) {
        setFirstName(signUpRequestDTO.getFirstName());
        setLastName(signUpRequestDTO.getLastName());
        setEmail(signUpRequestDTO.getEmail());
        setPassword(signUpRequestDTO.getPassword());
        setRole(Role.USER);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }
}
