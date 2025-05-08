package com.eventbooking;

import com.eventbooking.domain.enums.Role;
import com.eventbooking.service.UserService;
import com.eventbooking.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@SpringBootApplication
public class FullStackEventBookingSystemApplication{
	public static void main(String[] args) {
		SpringApplication.run(FullStackEventBookingSystemApplication.class, args);
	}
}
