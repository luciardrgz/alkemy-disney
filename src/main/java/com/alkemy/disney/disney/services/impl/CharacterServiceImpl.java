package com.alkemy.disney.disney.services.impl;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.TitleDTO;
import com.alkemy.disney.disney.entities.CharacterEntity;
import com.alkemy.disney.disney.entities.TitleEntity;
import com.alkemy.disney.disney.mappers.CharacterMapper;
import com.alkemy.disney.disney.repositories.CharacterRepository;
import com.alkemy.disney.disney.services.CharacterService;
import com.alkemy.disney.disney.services.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private TitleService titleService;

    // Saves a Character in Repository
    public CharacterDTO save(CharacterDTO dto, Long titleId)
    {
        CharacterEntity entity = this.characterMapper.characterDTO2Entity(dto);
        CharacterEntity savedEntity = this.characterRepository.save(entity);
        titleService.addCharacter(titleId, savedEntity.getId());
        CharacterDTO result = this.characterMapper.characterEntity2DTO(savedEntity);

        return result;
    }

    // Searches for an id in Characters Repository
    public CharacterEntity getCharacterById(Long id)
    {
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if (!entity.isPresent()) {
            // Error message (not found)
        }
        return entity.get();
    }

    // Searches for an id in Characters Repository and converts the entity into a DTO
    public CharacterDTO getCharacterDTOById(Long id){
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if (!entity.isPresent()) {
            // Error message (not found)
        }
        CharacterDTO characterDTO = this.characterMapper.characterEntity2DTO(entity.get());
        return characterDTO;
    }

}
