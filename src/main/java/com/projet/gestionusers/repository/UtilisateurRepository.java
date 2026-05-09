package com.projet.gestionusers.repository;

import com.projet.gestionusers.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByEmail(String email);
    boolean existsByEmail(String email);
    List<Utilisateur> findByActifTrue();
    List<Utilisateur> findByActifFalse();
    List<Utilisateur> findByNomContaining(String nom);
    List<Utilisateur> findByRole_Nom(String nomRole);
}