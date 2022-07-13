package com.alkemy.disney.disney.mappers;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entities.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {

    public GenreEntity genreDTO2Entity(GenreDTO dto)
    {
        GenreEntity entity = new GenreEntity();
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        return entity;
    }

    public GenreDTO genreEntity2DTO(GenreEntity entity)
    {
        GenreDTO dto = new GenreDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        return dto;
    }

    public List<GenreDTO> genreEntityList2DTOList (List<GenreEntity>entities)
    {
        List<GenreDTO>dtos = new ArrayList<>();

        for(GenreEntity entity : entities)
        {
            dtos.add(this.genreEntity2DTO(entity));
        }
        return dtos;
    }

    public List<GenreEntity> genreDTOList2EntityList(List<GenreDTO>dtos)
    {
        List<GenreEntity>entities = new ArrayList<>();

        for(GenreDTO dto : dtos)
        {
            entities.add(this.genreDTO2Entity(dto));
        }
        return entities;
    }
}
