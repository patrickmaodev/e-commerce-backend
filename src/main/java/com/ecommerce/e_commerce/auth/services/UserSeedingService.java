package com.ecommerce.e_commerce.auth.services;

import com.ecommerce.e_commerce.auth.entities.*;
import com.ecommerce.e_commerce.auth.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.util.Optional;

@Service
public class UserSeedingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    // This method is executed after the Spring context is initialized
    @PostConstruct
    public void seedSuperAdmin() {
        // Create or ensure the existence of the superadmin role
        createSuperAdminRole();

        // Create or ensure the existence of the superadmin user
        createSuperAdminUser();
    }

    // This method creates the "superadmin" role if it doesn't exist
    private void createSuperAdminRole() {
        try {
            // Find if the "superadmin" role already exists
            Role superAdminRole = roleRepository.findByRoleName("superadmin");

            if (superAdminRole == null) {
                // If the role doesn't exist, create it
                superAdminRole = new Role();
                superAdminRole.setRoleName("superadmin");
                superAdminRole.setDescription("Super Administrator");
                roleRepository.save(superAdminRole);
                System.out.println("Superadmin role created.");
            } else {
                System.out.println("Superadmin role already exists.");
            }
        } catch (Exception e) {
            // Handle exception, e.g., log it or rethrow a more specific exception
            System.err.println("Error creating superadmin role: " + e.getMessage());
        }
    }

    // This method creates the "superadmin" user if it doesn't exist
    private void createSuperAdminUser() {
        try {
            // Check if the "superadmin" user exists
            Optional<User> superAdminUserOpt = userRepository.findByEmail("superadmin@ecommerce.com");

            if (superAdminUserOpt.isEmpty()) {
                // Create the superadmin user
                User superAdminUser = new User();
                superAdminUser.setFirstName("Super");
                superAdminUser.setLastName("Admin");
                superAdminUser.setEmail("superadmin@ecommerce.com");
                superAdminUser.setPhoneNumber("1234567890");
                superAdminUser.setStatus("active");
                userRepository.save(superAdminUser);

                // Create the admin record for the superadmin user
                Admin admin = new Admin();
                admin.setUser(superAdminUser);
                adminRepository.save(admin);

                // Assign the "superadmin" role to the user
                Role superAdminRole = roleRepository.findByRoleName("superadmin");

                if (superAdminRole != null) {
                    UserRole userRole = new UserRole();
                    userRole.setUser(superAdminUser);
                    userRole.setRole(superAdminRole);
                    userRoleRepository.save(userRole);
                    System.out.println("Superadmin user created successfully!");
                } else {
                    System.err.println("Superadmin role does not exist, cannot assign role to user.");
                }
            } else {
                System.out.println("Superadmin user already exists.");
            }
        } catch (Exception e) {
            System.err.println("Error creating superadmin user: " + e.getMessage());
        }
    }


}
