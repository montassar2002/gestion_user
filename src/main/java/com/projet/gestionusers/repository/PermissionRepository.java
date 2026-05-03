package com.projet.gestionusers.repository;

import com.projet.gestionusers.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    List<Permission> findByNomContaining(String nom);
}