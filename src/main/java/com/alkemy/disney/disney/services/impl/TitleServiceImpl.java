package com.alkemy.disney.disney.services.impl;

import com.alkemy.disney.disney.dto.TitleDTO;
import com.alkemy.disney.disney.entities.CharacterEntity;
import com.alkemy.disney.disney.entities.TitleEntity;
import com.alkemy.disney.disney.exceptions.ParamNotFound;
import com.alkemy.disney.disney.mappers.TitleMapper;
import com.alkemy.disney.disney.repositories.TitleRepository;
import com.alkemy.disney.disney.services.CharacterService;
import com.alkemy.disney.disney.services.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TitleServiceImpl implements TitleService {

    @Autowired
    private TitleMapper titleMapper;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private CharacterService characterService;

    // Saves a title in Titles Repository
    public TitleDTO save(TitleDTO dto)
    {
        TitleEntity entity = titleMapper.titleDTO2Entity(dto);
        TitleEntity savedEntity = titleRepository.save(entity);
        TitleDTO result = titleMapper.titleEntity2DTO(savedEntity, true);
        return result;
    }

    // Searches for an id in Titles Repository
    public TitleEntity getTitleById(Long id) {
        Optional<TitleEntity> entity = titleRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Title ID not found");
        }
        return entity.get();
    }

    // Searches for an id in Titles Repository and converts the entity into a DTO
    public TitleDTO getTitleDTOById(Long id){
        Optional<TitleEntity> entity = titleRepository.findById(id);
        if(!entity.isPresent()) {
            throw new ParamNotFound("Title ID not found");
        }
        TitleDTO titleDTO = this.titleMapper.titleEntity2DTO(entity.get(), true);
        return titleDTO;
    }

    // Adds a Character to a list of Characters in a Title
    public void addCharacter(Long titleId, Long characterId)
    {
        TitleEntity titleEntity = getTitleById(titleId);
        List<CharacterEntity>entities = titleEntity.getCharacters();

        entities.add(characterService.getCharacterById(characterId));
        titleEntity.setCharacters(entities);
        titleRepository.save(titleEntity);
    }

    // Invokes a mapper method and modifies Title values
    public TitleDTO updateTitle(Long id, TitleDTO titleDTO){
        Optional<TitleEntity>entity = titleRepository.findById(id);

        if(!entity.isPresent())
        {
            throw new ParamNotFound("Title ID to modify not found");
        }
        this.titleMapper.modifyTitleValues(entity.get(),titleDTO);
        TitleEntity savedEntity = this.titleRepository.save(entity.get());
        TitleDTO result = this.titleMapper.titleEntity2DTO(savedEntity, true);

        return result;
    }
}