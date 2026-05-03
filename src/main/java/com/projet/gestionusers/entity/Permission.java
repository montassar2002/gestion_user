package com.projet.gestionusers.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPermission;

    @NotBlank(message = "Le nom est obligatoire")
    @Column(unique = true)
    private String nom;

    private String description;
}