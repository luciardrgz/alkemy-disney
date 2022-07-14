package com.alkemy.disney.disney.services;


import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entities.CharacterEntity;

import java.util.List;

public interface CharacterService {

    CharacterDTO save(CharacterDTO dto, Long titleId);

    CharacterEntity getCharacterById(Long id);

    CharacterDTO getCharacterDTOById(Long id);

    CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO);

    List<CharacterDTO> getByFilters(String name, Integer age, Integer weight, List<Long>titles, String order);

}