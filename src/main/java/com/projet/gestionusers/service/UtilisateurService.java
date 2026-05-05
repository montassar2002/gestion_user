package com.projet.gestionusers.service;

import com.projet.gestionusers.dto.UtilisateurDTO;
import com.projet.gestionusers.entity.HistoriqueAction;
import com.projet.gestionusers.entity.Utilisateur;
import com.projet.gestionusers.mapper.UtilisateurMapper;
import com.projet.gestionusers.repository.HistoriqueActionRepository;
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

    public List<UtilisateurDTO> getAll() {
        return utilisateurMapper.toListDto(utilisateurRepository.findAll());
    }

    public UtilisateurDTO getById(int id) {
        Utilisateur u = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return utilisateurMapper.toDto(u);
    }

    public UtilisateurDTO create(UtilisateurDTO dto) {
        if (utilisateurRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email déjà utilisé");
        }
        Utilisateur u = utilisateurMapper.fromDto(dto);
        u.setMotDePasse(passwordEncoder.encode(dto.getMotDePasse()));
        Utilisateur saved = utilisateurRepository.save(u);
        saveHistorique(saved, "Création utilisateur : " + saved.getEmail());
        return utilisateurMapper.toDto(saved);
    }

    public UtilisateurDTO update(int id, UtilisateurDTO dto) {
        Utilisateur existing = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        existing.setNom(dto.getNom());
        existing.setEmail(dto.getEmail());
        existing.setActif(dto.isActif());
        if (dto.getMotDePasse() != null && !dto.getMotDePasse().isEmpty()) {
            existing.setMotDePasse(passwordEncoder.encode(dto.getMotDePasse()));
        }
        Utilisateur saved = utilisateurRepository.save(existing);
        saveHistorique(saved, "Modification utilisateur : " + saved.getEmail());
        return utilisateurMapper.toDto(saved);
    }

    public void delete(int id) {
        Utilisateur u = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        saveHistorique(u, "Suppression utilisateur : " + u.getEmail());
        utilisateurRepository.deleteById(id);
    }

    public List<UtilisateurDTO> getActifs() {
        return utilisateurMapper.toListDto(utilisateurRepository.findByActifTrue());
    }

    private void saveHistorique(Utilisateur u, String action) {
        HistoriqueAction h = new HistoriqueAction();
        h.setUtilisateur(u);
        h.setAction(action);
        historiqueRepository.save(h);
    }
}