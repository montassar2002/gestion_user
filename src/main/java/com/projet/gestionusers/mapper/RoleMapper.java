package com.projet.gestionusers.mapper;

import com.projet.gestionusers.dto.RoleDTO;
import com.projet.gestionusers.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    @Autowired
    private ModelMapper modelMapper;

    public RoleDTO toDto(Role r) {
        return modelMapper.map(r, RoleDTO.class);
    }

    public Role fromDto(RoleDTO dto) {
        return modelMapper.map(dto, Role.class);
    }

    public List<RoleDTO> toListDto(List<Role> list) {
        return list.stream()
                .map(r -> modelMapper.map(r, RoleDTO.class))
                .collect(Collectors.toList());
    }
}