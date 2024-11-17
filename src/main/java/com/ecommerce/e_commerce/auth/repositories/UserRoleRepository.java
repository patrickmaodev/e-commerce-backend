package com.ecommerce.e_commerce.auth.repositories;

import com.ecommerce.e_commerce.auth.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, String> {
}
