package com.ecommerce.e_commerce.auth.services;

import com.ecommerce.e_commerce.auth.entities.*;
import com.ecommerce.e_commerce.auth.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

@Service
public class RolePermissionSeedingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;  // Inject RolePermissionRepository

    // This method is executed after the Spring context is initialized
    @PostConstruct
    public void seedRolesAndPermissions() {
        // Create or ensure the existence of roles
        createRolesAndPermissions();
    }

    // This method creates roles and assigns permissions
    private void createRolesAndPermissions() {
        // Create roles
        createRole("admin", "Administrator with full access");
        createRole("vendor", "Vendor with access to manage their products");
        createRole("customer", "Customer with access to browse and place orders");

        // Create permissions
        createPermission("create_product", "Permission to create products");
        createPermission("view_order", "Permission to view orders");
        createPermission("manage_user", "Permission to manage users");
        createPermission("browse_product", "Permission to browse products");
        createPermission("place_order", "Permission to place an order");

        // Assign permissions to roles (admin gets all permissions, others get limited permissions)
        assignPermissionsToRole("admin", "create_product", "view_order", "manage_user");
        assignPermissionsToRole("vendor", "view_order", "create_product");
        assignPermissionsToRole("customer", "browse_product", "place_order");
    }

    // Helper method to create roles if they don't exist
    private void createRole(String roleName, String description) {
        Role role = roleRepository.findByRoleName(roleName);
        if (role == null) {
            role = new Role();
            role.setRoleName(roleName);
            role.setDescription(description);
            roleRepository.save(role);
            System.out.println(roleName + " role created.");
        } else {
            System.out.println(roleName + " role already exists.");
        }
    }

    // Helper method to create permissions if they don't exist
    private void createPermission(String permissionName, String description) {
        Permission permission = permissionRepository.findByPermissionName(permissionName);
        if (permission == null) {
            permission = new Permission();
            permission.setPermissionName(permissionName);
            permission.setDescription(description);
            permissionRepository.save(permission);
            System.out.println(permissionName + " permission created.");
        } else {
            System.out.println(permissionName + " permission already exists.");
        }
    }

    // Helper method to assign permissions to roles
    private void assignPermissionsToRole(String roleName, String... permissionNames) {
        Role role = roleRepository.findByRoleName(roleName);
        if (role != null) {
            for (String permissionName : permissionNames) {
                Permission permission = permissionRepository.findByPermissionName(permissionName);
                if (permission != null) {
                    // Create a role-permission association
                    RolePermission rolePermission = new RolePermission();
                    rolePermission.setRole(role);
                    rolePermission.setPermission(permission);
                    rolePermissionRepository.save(rolePermission);
                    System.out.println("Assigned " + permissionName + " to " + roleName + " role.");
                }
            }
        }
    }
}
