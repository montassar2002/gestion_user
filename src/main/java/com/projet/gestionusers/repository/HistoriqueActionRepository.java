package com.projet.gestionusers.repository;

import com.projet.gestionusers.entity.HistoriqueAction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistoriqueActionRepository extends JpaRepository<HistoriqueAction, Integer> {
    List<HistoriqueAction> findByUtilisateurIdUtilisateur(int idUtilisateur);
}