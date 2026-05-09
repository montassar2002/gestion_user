package com.projet.gestionusers.controller;

import com.projet.gestionusers.dto.RoleDTO;
import com.projet.gestionusers.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAll() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(roleService.getById(id));
    }

    @GetMapping("/recherche")
    public ResponseEntity<List<RoleDTO>> rechercherParNom(@RequestParam String nom) {
        return ResponseEntity.ok(roleService.rechercherParNom(nom));
    }

    @PostMapping
    public ResponseEntity<RoleDTO> create(@Valid @RequestBody RoleDTO dto) {
        return ResponseEntity.ok(roleService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> update(@PathVariable int id, @Valid @RequestBody RoleDTO dto) {
        return ResponseEntity.ok(roleService.update(id, dto));
    }

    @PostMapping("/{idRole}/permissions/{idPermission}")
    public ResponseEntity<RoleDTO> ajouterPermission(@PathVariable int idRole, @PathVariable int idPermission) {
        return ResponseEntity.ok(roleService.ajouterPermission(idRole, idPermission));
    }

    @DeleteMapping("/{idRole}/permissions/{idPermission}")
    public ResponseEntity<RoleDTO> retirerPermission(@PathVariable int idRole, @PathVariable int idPermission) {
        return ResponseEntity.ok(roleService.retirerPermission(idRole, idPermission));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}