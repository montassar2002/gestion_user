package com.projet.gestionusers.service;

import com.projet.gestionusers.dto.RoleDTO;
import com.projet.gestionusers.entity.Role;
import com.projet.gestionusers.mapper.RoleMapper;
import com.projet.gestionusers.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    public List<RoleDTO> getAll() {
        return roleMapper.toListDto(roleRepository.findAll());
    }

    public RoleDTO getById(int id) {
        Role r = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé"));
        return roleMapper.toDto(r);
    }

    public RoleDTO create(RoleDTO dto) {
        Role r = roleMapper.fromDto(dto);
        return roleMapper.toDto(roleRepository.save(r));
    }

    public RoleDTO update(int id, RoleDTO dto) {
        Role existing = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé"));
        existing.setNom(dto.getNom());
        existing.setDescription(dto.getDescription());
        return roleMapper.toDto(roleRepository.save(existing));
    }

    public void delete(int id) {
        roleRepository.deleteById(id);
    }
}