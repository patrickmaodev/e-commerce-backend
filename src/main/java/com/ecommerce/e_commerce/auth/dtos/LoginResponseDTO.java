package com.ecommerce.e_commerce.auth.dtos;

import com.ecommerce.e_commerce.auth.entities.Role;
import com.ecommerce.e_commerce.auth.entities.User;

public class LoginResponseDTO {

    private String token;
    private User user;
    private Role role;
    private String errorMessage;

    // Constructor for successful login
    public LoginResponseDTO(User user, Role role, String token) {
        this.user = user;
        this.role = role;
        this.token = token;
        this.errorMessage = null; // Ensure errorMessage is null for success
    }

    // Constructor for error response
    public LoginResponseDTO(String errorMessage) {
        this.errorMessage = errorMessage;
        this.token = null; // Ensure token is null for errors
        this.user = null;
        this.role = null;
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
