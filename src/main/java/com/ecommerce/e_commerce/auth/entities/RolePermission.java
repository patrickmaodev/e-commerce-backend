package com.ecommerce.e_commerce.auth.entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "role_permissions")
public class RolePermission {

    @Id
    @Column(name = "role_permission_id", nullable = false, updatable = false)
    private UUID rolePermissionId = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

    // Getters and Setters

    public UUID getRolePermissionId() {
        return rolePermissionId;
    }

    public void setRolePermissionId(UUID rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
