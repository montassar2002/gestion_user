package com.projet.gestionusers.mapper;

import com.projet.gestionusers.dto.PermissionDTO;
import com.projet.gestionusers.entity.Permission;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissionMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PermissionDTO toDto(Permission p) {
        return modelMapper.map(p, PermissionDTO.class);
    }

    public Permission fromDto(PermissionDTO dto) {
        return modelMapper.map(dto, Permission.class);
    }

    public List<PermissionDTO> toListDto(List<Permission> list) {
        return list.stream()
                .map(p -> modelMapper.map(p, PermissionDTO.class))
                .collect(Collectors.toList());
    }
}