package com.projet.gestionusers.service;

import com.projet.gestionusers.dto.LoginRequest;
import com.projet.gestionusers.dto.LoginResponse;
import com.projet.gestionusers.entity.Utilisateur;
import com.projet.gestionusers.repository.UtilisateurRepository;
import com.projet.gestionusers.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public LoginResponse login(LoginRequest request) {
        Utilisateur u = utilisateurRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email ou mot de passe incorrect"));

        if (!passwordEncoder.matches(request.getMotDePasse(), u.getMotDePasse())) {
            throw new RuntimeException("Email ou mot de passe incorrect");
        }

        if (!u.isActif()) {
            throw new RuntimeException("Compte désactivé");
        }

        String token = jwtUtils.generateToken(u.getEmail());
        return new LoginResponse(token, u.getNom(), u.getEmail(), u.getRole().getNom());
    }
}