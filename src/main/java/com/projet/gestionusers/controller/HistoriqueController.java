package com.projet.gestionusers.controller;

import com.projet.gestionusers.entity.HistoriqueAction;
import com.projet.gestionusers.repository.HistoriqueActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/historique")
@CrossOrigin(origins = "*")
public class HistoriqueController {

    @Autowired
    private HistoriqueActionRepository historiqueRepository;

    @GetMapping
    public ResponseEntity<List<HistoriqueAction>> getAll() {
        return ResponseEntity.ok(historiqueRepository.findAllByOrderByDateDesc());
    }

    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<List<HistoriqueAction>> getByUtilisateur(@PathVariable int id) {
        return ResponseEntity.ok(historiqueRepository.findByUtilisateurIdUtilisateur(id));
    }

    @GetMapping("/action/{action}")
    public ResponseEntity<List<HistoriqueAction>> getByAction(@PathVariable String action) {
        return ResponseEntity.ok(historiqueRepository.findByAction(action));
    }
}