package com.projet.gestionusers.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
public class RoleDTO {
    private int idRole;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    private String description;

    private List<PermissionDTO> permissions;
}