package com.projet.gestionusers.service;

import com.projet.gestionusers.dto.PermissionDTO;
import com.projet.gestionusers.entity.Permission;
import com.projet.gestionusers.mapper.PermissionMapper;
import com.projet.gestionusers.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionMapper permissionMapper;

    public List<PermissionDTO> getAll() {
        return permissionMapper.toListDto(permissionRepository.findAll());
    }

    public PermissionDTO getById(int id) {
        Permission p = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission non trouvée"));
        return permissionMapper.toDto(p);
    }

    public PermissionDTO create(PermissionDTO dto) {
        Permission p = permissionMapper.fromDto(dto);
        return permissionMapper.toDto(permissionRepository.save(p));
    }

    public PermissionDTO update(int id, PermissionDTO dto) {
        Permission existing = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission non trouvée"));
        existing.setNom(dto.getNom());
        existing.setDescription(dto.getDescription());
        return permissionMapper.toDto(permissionRepository.save(existing));
    }

    public void delete(int id) {
        permissionRepository.deleteById(id);
    }
}