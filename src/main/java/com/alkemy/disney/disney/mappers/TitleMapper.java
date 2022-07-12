package com.alkemy.disney.disney.mappers;

import com.alkemy.disney.disney.dto.TitleDTO;
import com.alkemy.disney.disney.entities.CharacterEntity;
import com.alkemy.disney.disney.entities.TitleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TitleMapper {

    @Lazy
    @Autowired
    private CharacterMapper characterMapper;

    public TitleEntity titleDTO2Entity(TitleDTO dto){
        TitleEntity entity = new TitleEntity();
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setCreationDate(dto.getCreationDate());
        entity.setScore(dto.getScore());
        entity.setGenreId(dto.getGenreId());

        List<CharacterEntity> characterEntities = this.characterMapper.characterDTO2EntityList(dto.getCharacters());

        return entity;
    }

    public TitleDTO titleEntity2DTO(TitleEntity entity) {
        TitleDTO dto = new TitleDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setGenreId(entity.getGenreId());
        return dto;
    }

    public List<TitleDTO> titleEntity2DTOList(List<TitleEntity>entities)
    {
        List<TitleDTO>dtos = new ArrayList<>();

        for(TitleEntity entity : entities)
        {
            dtos.add(this.titleEntity2DTO(entity));
        }
        return dtos;
    }

    public List<TitleEntity> titleDTO2EntityList(List<TitleDTO>dtos)
    {
        List<TitleEntity>entities = new ArrayList<>();

        for(TitleDTO dto : dtos)
        {
            entities.add(this.titleDTO2Entity(dto));
        }
        return entities;
    }
}
