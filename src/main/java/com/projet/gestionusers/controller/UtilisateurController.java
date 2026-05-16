package com.projet.gestionusers.controller;

import com.projet.gestionusers.dto.UtilisateurDTO;
import com.projet.gestionusers.service.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins = "*")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ_USERS')")
    public ResponseEntity<List<UtilisateurDTO>> getAll() {
        return ResponseEntity.ok(utilisateurService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_USERS')")
    public ResponseEntity<UtilisateurDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(utilisateurService.getById(id));
    }

    @GetMapping("/actifs")
    @PreAuthorize("hasAuthority('READ_USERS')")
    public ResponseEntity<List<UtilisateurDTO>> getActifs() {
        return ResponseEntity.ok(utilisateurService.getActifs());
    }

    @GetMapping("/inactifs")
    @PreAuthorize("hasAuthority('READ_USERS')")
    public ResponseEntity<List<UtilisateurDTO>> getInactifs() {
        return ResponseEntity.ok(utilisateurService.getInactifs());
    }

    @GetMapping("/recherche")
    @PreAuthorize("hasAuthority('READ_USERS')")
    public ResponseEntity<List<UtilisateurDTO>> rechercherParNom(@RequestParam String nom) {
        return ResponseEntity.ok(utilisateurService.rechercherParNom(nom));
    }

    @GetMapping("/role/{nomRole}")
    @PreAuthorize("hasAuthority('READ_USERS')")
    public ResponseEntity<List<UtilisateurDTO>> rechercherParRole(@PathVariable String nomRole) {
        return ResponseEntity.ok(utilisateurService.rechercherParRole(nomRole));
    }

    @PostMapping
    
    public ResponseEntity<UtilisateurDTO> create(@Valid @RequestBody UtilisateurDTO dto) {
        return ResponseEntity.ok(utilisateurService.create(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_USERS')")
    public ResponseEntity<UtilisateurDTO> update(@PathVariable int id, @Valid @RequestBody UtilisateurDTO dto) {
        return ResponseEntity.ok(utilisateurService.update(id, dto));
    }

    @PatchMapping("/{id}/toggle")
    @PreAuthorize("hasAuthority('UPDATE_USERS')")
    public ResponseEntity<UtilisateurDTO> toggleActif(@PathVariable int id) {
        return ResponseEntity.ok(utilisateurService.toggleActif(id));
    }

    @DeleteMapping("/{id}")
    
    public ResponseEntity<Void> delete(@PathVariable int id) {
        utilisateurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}