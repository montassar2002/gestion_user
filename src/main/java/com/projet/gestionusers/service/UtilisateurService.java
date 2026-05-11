package com.projet.gestionusers.service;

import com.projet.gestionusers.dto.UtilisateurDTO;
import com.projet.gestionusers.entity.HistoriqueAction;
import com.projet.gestionusers.entity.Role;
import com.projet.gestionusers.entity.Utilisateur;
import com.projet.gestionusers.mapper.UtilisateurMapper;
import com.projet.gestionusers.repository.HistoriqueActionRepository;
import com.projet.gestionusers.repository.RoleRepository;
import com.projet.gestionusers.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private HistoriqueActionRepository historiqueRepository;

    @Autowired
    private UtilisateurMapper utilisateurMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    public List<UtilisateurDTO> getAll() {
        return utilisateurMapper.toListDto(utilisateurRepository.findAll());
    }

    public UtilisateurDTO getById(int id) {
        Utilisateur u = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return utilisateurMapper.toDto(u);
    }

    public List<UtilisateurDTO> getActifs() {
        return utilisateurMapper.toListDto(utilisateurRepository.findByActifTrue());
    }

    public List<UtilisateurDTO> getInactifs() {
        return utilisateurMapper.toListDto(utilisateurRepository.findByActifFalse());
    }

    public List<UtilisateurDTO> rechercherParNom(String nom) {
        return utilisateurMapper.toListDto(utilisateurRepository.findByNomContaining(nom));
    }

    public List<UtilisateurDTO> rechercherParRole(String nomRole) {
        return utilisateurMapper.toListDto(utilisateurRepository.findByRole_Nom(nomRole));
    }

    public UtilisateurDTO create(UtilisateurDTO dto) {
        if (utilisateurRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email déjà utilisé");
        }
        Role role = roleRepository.findById(dto.getRole().getIdRole())
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé"));

        Utilisateur u = new Utilisateur();
        u.setNom(dto.getNom());
        u.setEmail(dto.getEmail());
        u.setMotDePasse(passwordEncoder.encode(dto.getMotDePasse()));
        u.setActif(dto.isActif());
        u.setRole(role);

        Utilisateur saved = utilisateurRepository.save(u);
        saveHistorique(saved, "CREATE_USER");
        return utilisateurMapper.toDto(saved);
    }

    public UtilisateurDTO update(int id, UtilisateurDTO dto) {
        Utilisateur existing = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Role role = roleRepository.findById(dto.getRole().getIdRole())
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé"));

        existing.setNom(dto.getNom());
        existing.setEmail(dto.getEmail());
        existing.setActif(dto.isActif());
        existing.setRole(role);

        if (dto.getMotDePasse() != null && !dto.getMotDePasse().isEmpty()) {
            existing.setMotDePasse(passwordEncoder.encode(dto.getMotDePasse()));
        }

        Utilisateur saved = utilisateurRepository.save(existing);
        saveHistorique(saved, "UPDATE_USER");
        return utilisateurMapper.toDto(saved);
    }

    public UtilisateurDTO toggleActif(int id) {
        Utilisateur u = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        u.setActif(!u.isActif());
        Utilisateur saved = utilisateurRepository.save(u);
        saveHistorique(saved, u.isActif() ? "ACTIVATE_USER" : "DEACTIVATE_USER");
        return utilisateurMapper.toDto(saved);
    }

    public void delete(int id) {
        Utilisateur u = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        
        // Supprimer d'abord l'historique lié à cet utilisateur
        List<HistoriqueAction> historiques = historiqueRepository.findByUtilisateurIdUtilisateur(id);
        historiqueRepository.deleteAll(historiques);
        
        // Ensuite supprimer l'utilisateur
        utilisateurRepository.deleteById(id);
    }

    private void saveHistorique(Utilisateur u, String action) {
        HistoriqueAction h = new HistoriqueAction();
        h.setUtilisateur(u);
        h.setAction(action);
        historiqueRepository.save(h);
    }
}