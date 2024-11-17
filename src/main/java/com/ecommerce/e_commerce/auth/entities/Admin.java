package com.ecommerce.e_commerce.auth.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "admins")
public class Admin {
    @Id
    @Column(name = "admin_id", nullable = false, updatable = false)
    private UUID adminId = UUID.randomUUID();

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String adminSpecificField1;
    private String adminSpecificField2;

    // Getters and Setters
    public UUID getAdminId() {
        return adminId;
    }

    public void setAdminId(UUID adminId) {
        this.adminId = adminId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAdminSpecificField1() {
        return adminSpecificField1;
    }

    public void setAdminSpecificField1(String adminSpecificField1) {
        this.adminSpecificField1 = adminSpecificField1;
    }

    public String getAdminSpecificField2() {
        return adminSpecificField2;
    }

    public void setAdminSpecificField2(String adminSpecificField2) {
        this.adminSpecificField2 = adminSpecificField2;
    }
}
