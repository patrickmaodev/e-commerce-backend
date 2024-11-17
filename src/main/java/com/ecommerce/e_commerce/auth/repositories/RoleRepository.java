package com.ecommerce.e_commerce.auth.repositories;

import com.ecommerce.e_commerce.auth.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByRoleName(String roleName);
}
