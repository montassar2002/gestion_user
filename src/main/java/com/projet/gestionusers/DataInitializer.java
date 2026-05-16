package com.projet.gestionusers;

import com.projet.gestionusers.entity.Permission;
import com.projet.gestionusers.entity.Role;
import com.projet.gestionusers.entity.Utilisateur;
import com.projet.gestionusers.repository.PermissionRepository;
import com.projet.gestionusers.repository.RoleRepository;
import com.projet.gestionusers.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // Crée les permissions si elles n'existent pas
        Permission readUsers = createPermission("READ_USERS", "Lire les utilisateurs");
        Permission createUsers = createPermission("CREATE_USERS", "Créer un utilisateur");
        Permission updateUsers = createPermission("UPDATE_USERS", "Modifier un utilisateur");
        Permission deleteUsers = createPermission("DELETE_USERS", "Supprimer un utilisateur");

        // Crée le rôle ADMIN si il n'existe pas
        Role adminRole = roleRepository.findByNom("ADMIN").orElseGet(() -> {
            Role role = new Role();
            role.setNom("ADMIN");
            role.setDescription("Administrateur");
            role.getPermissions().add(readUsers);
            role.getPermissions().add(createUsers);
            role.getPermissions().add(updateUsers);
            role.getPermissions().add(deleteUsers);
            return roleRepository.save(role);
        });

        // Crée le rôle USER si il n'existe pas
        roleRepository.findByNom("USER").orElseGet(() -> {
            Role role = new Role();
            role.setNom("USER");
            role.setDescription("Utilisateur simple");
            role.getPermissions().add(readUsers);
            return roleRepository.save(role);
        });

        // Crée l'utilisateur admin si il n'existe pas
        if (!utilisateurRepository.existsByEmail("montassar@gmail.com")) {
            Utilisateur admin = new Utilisateur();
            admin.setNom("Montassar");
            admin.setEmail("montassar@gmail.com");
            admin.setMotDePasse(passwordEncoder.encode("123456"));
            admin.setActif(true);
            admin.setRole(adminRole);
            utilisateurRepository.save(admin);
        }

        System.out.println("✅ Données initialisées avec succès !");
    }

    private Permission createPermission(String nom, String description) {
        return permissionRepository.findByNomContaining(nom)
                .stream()
                .findFirst()
                .orElseGet(() -> {
                    Permission p = new Permission();
                    p.setNom(nom);
                    p.setDescription(description);
                    return permissionRepository.save(p);
                });
    }
}