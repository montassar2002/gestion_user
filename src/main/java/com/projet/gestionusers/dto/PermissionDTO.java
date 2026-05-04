package com.projet.gestionusers.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PermissionDTO {
    private int idPermission;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    private String description;
}