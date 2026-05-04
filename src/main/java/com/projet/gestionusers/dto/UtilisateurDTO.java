package com.projet.gestionusers.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UtilisateurDTO {
    private int idUtilisateur;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank
    @Email(message = "Email invalide")
    private String email;

    @NotBlank
    private String motDePasse;

    private boolean actif;

    private RoleDTO role;
}