package com.alkemy.disney.disney.services;

import com.alkemy.disney.disney.dto.TitleDTO;
import com.alkemy.disney.disney.entities.TitleEntity;

public interface TitleService {

    TitleDTO save(TitleDTO dto);

    TitleEntity getTitleById(Long id);

    void addCharacter(Long titleId, Long characterId);

    TitleDTO getTitleDTOById(Long id);
}
