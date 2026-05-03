package com.projet.gestionusers.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRole;

    @NotBlank(message = "Le nom est obligatoire")
    @Column(unique = true)
    private String nom;

    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "role_permission",
        joinColumns = @JoinColumn(name = "idRole"),
        inverseJoinColumns = @JoinColumn(name = "idPermission")
    )
    private List<Permission> permissions = new ArrayList<>();
}