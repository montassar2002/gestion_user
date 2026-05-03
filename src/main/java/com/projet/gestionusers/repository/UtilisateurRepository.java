package com.projet.gestionusers.repository;

import com.projet.gestionusers.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByEmail(String email);
    List<Utilisateur> findByActifTrue();
    List<Utilisateur> findByNomContaining(String nom);
    boolean existsByEmail(String email);
}