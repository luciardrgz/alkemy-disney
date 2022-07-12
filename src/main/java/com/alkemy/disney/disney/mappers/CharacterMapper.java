package com.alkemy.disney.disney.mappers;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.TitleDTO;
import com.alkemy.disney.disney.entities.CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {

    @Lazy
    @Autowired
    public TitleMapper titleMapper;

    public CharacterEntity characterDTO2Entity(CharacterDTO dto){
        CharacterEntity entity = new CharacterEntity();
        entity.setImage(dto.getImage());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setStory(dto.getStory());

        return entity;
    }

    public CharacterDTO characterEntity2DTO(CharacterEntity entity){
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setStory(entity.getStory());

        List<TitleDTO>titleDTOS = this.titleMapper.titleEntity2DTOList(entity.getTitles());
        dto.setTitles(titleDTOS);

        return dto;
    }

    public List<CharacterDTO> characterEntity2DTOList(List<CharacterEntity>entities)
    {
        List<CharacterDTO>dtos = new ArrayList<>();

        for(CharacterEntity entity : entities)
        {
            dtos.add(this.characterEntity2DTO(entity));
        }
        return dtos;
    }

    public List<CharacterEntity> characterDTO2EntityList(List<CharacterDTO>dtos)
    {
        List<CharacterEntity>entities = new ArrayList<>();

        for(CharacterDTO dto : dtos)
        {
            entities.add(this.characterDTO2Entity(dto));
        }

        return entities;
    }

}
