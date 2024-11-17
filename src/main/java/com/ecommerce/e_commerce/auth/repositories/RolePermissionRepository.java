package com.ecommerce.e_commerce.auth.repositories;

import com.ecommerce.e_commerce.auth.entities.Permission;
import com.ecommerce.e_commerce.auth.entities.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RolePermissionRepository extends JpaRepository<RolePermission, UUID> {
}
