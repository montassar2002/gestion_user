package com.projet.gestionusers.mapper;

import com.projet.gestionusers.dto.UtilisateurDTO;
import com.projet.gestionusers.entity.Utilisateur;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UtilisateurMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UtilisateurDTO toDto(Utilisateur u) {
        return modelMapper.map(u, UtilisateurDTO.class);
    }

    public Utilisateur fromDto(UtilisateurDTO dto) {
        return modelMapper.map(dto, Utilisateur.class);
    }

    public List<UtilisateurDTO> toListDto(List<Utilisateur> list) {
        return list.stream()
                .map(u -> modelMapper.map(u, UtilisateurDTO.class))
                .collect(Collectors.toList());
    }
}