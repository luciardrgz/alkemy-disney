package com.alkemy.disney.disney.services;

import com.alkemy.disney.disney.dto.TitleDTO;
import com.alkemy.disney.disney.entities.TitleEntity;

import java.util.List;

public interface TitleService {

    TitleDTO save(TitleDTO dto);

    TitleEntity getTitleById(Long id);

    void addCharacter(Long titleId, Long characterId);

    void removeCharacter(Long titleId, Long characterId);

    List<TitleDTO> getByFilters(String name, Long genreId, String order);

    TitleDTO getTitleDTOById(Long id);

    TitleDTO updateTitle(Long id, TitleDTO titleDTO);

    void delete(Long id);
}
