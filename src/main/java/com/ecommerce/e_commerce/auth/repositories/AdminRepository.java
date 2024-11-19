package com.ecommerce.e_commerce.auth.repositories;

import com.ecommerce.e_commerce.auth.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
}