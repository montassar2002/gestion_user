package com.projet.gestionusers.security;

import com.projet.gestionusers.entity.Utilisateur;
import com.projet.gestionusers.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur u = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + email));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // Ajouter le rôle
        authorities.add(new SimpleGrantedAuthority("ROLE_" + u.getRole().getNom()));

        // Ajouter les permissions du rôle
        u.getRole().getPermissions().forEach(p ->
            authorities.add(new SimpleGrantedAuthority(p.getNom()))
        );

        return new User(u.getEmail(), u.getMotDePasse(), authorities);
    }
}