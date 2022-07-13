package com.alkemy.disney.disney.services;


import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entities.CharacterEntity;

public interface CharacterService {

    CharacterDTO save(CharacterDTO dto, Long titleId);

    CharacterEntity getCharacterById(Long id);

    characterDTO getCharacterDTOById(Long id);

}
