package com.ecommerce.e_commerce.auth.controllers;

import com.ecommerce.e_commerce.auth.dtos.LoginRequestDTO;
import com.ecommerce.e_commerce.auth.dtos.LoginResponseDTO;
import com.ecommerce.e_commerce.auth.services.LoginService;
import com.ecommerce.e_commerce.auth.entities.User;
import com.ecommerce.e_commerce.auth.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/admin")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            // Perform login and get the JWT token
            String token = loginService.login(loginRequestDTO);

            // Fetch the user and their role
            User user = loginService.getUserByEmail(loginRequestDTO.getEmail());
            Role role = user.getRole();

            // Prepare the successful response
            LoginResponseDTO response = new LoginResponseDTO(user, role, token);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // Handle authentication failures with appropriate error response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponseDTO("Invalid credentials"));
        }
    }
}
