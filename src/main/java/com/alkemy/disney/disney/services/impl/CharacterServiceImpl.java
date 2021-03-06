package com.alkemy.disney.disney.services.impl;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entities.CharacterEntity;
import com.alkemy.disney.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.disney.exceptions.ParamNotFound;
import com.alkemy.disney.disney.mappers.CharacterMapper;
import com.alkemy.disney.disney.repositories.CharacterRepository;
import com.alkemy.disney.disney.services.CharacterService;
import com.alkemy.disney.disney.services.TitleService;
import com.alkemy.disney.disney.repositories.specifications.CharacterSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    private CharacterMapper characterMapper;
    private CharacterRepository characterRepository;
    private TitleService titleService;
    private CharacterSpecification characterSpecification;

    @Lazy
    @Autowired
    public CharacterServiceImpl(CharacterMapper characterMapper,
                                CharacterRepository characterRepository,
                                TitleService titleService,
                                CharacterSpecification characterSpecification)
    {
        this.characterMapper = characterMapper;
        this.characterRepository = characterRepository;
        this.titleService = titleService;
        this.characterSpecification = characterSpecification;
    }


    // Saves a Character in Repository
    public CharacterDTO save(CharacterDTO dto, Long titleId)
    {
        CharacterEntity entity = this.characterMapper.characterDTO2Entity(dto);
        CharacterEntity savedEntity = this.characterRepository.save(entity);
        titleService.addCharacter(titleId, savedEntity.getId());
        CharacterDTO result = this.characterMapper.characterEntity2DTO(savedEntity,true);

        return result;
    }

    // Searches for an id in Characters Repository
    public CharacterEntity getCharacterById(Long id)
    {
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if(!entity.isPresent()) {
            throw new ParamNotFound("Character ID not found");
        }
        return entity.get();
    }

    // Searches for an id in Characters Repository and converts the entity into a DTO
    public CharacterDTO getCharacterDTOById(Long id){
        Optional<CharacterEntity> entity = characterRepository.findById(id);
        if(!entity.isPresent()) {
            throw new ParamNotFound("Character ID not found");
        }
        CharacterDTO characterDTO = this.characterMapper.characterEntity2DTO(entity.get(),true);
        return characterDTO;
    }

    // Character search filtered by Name, Age, Weight, Titles - has ASC/DESC order
    public List<CharacterDTO> getByFilters(String name, Integer age, Integer weight, List<Long>titles)
    {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, weight, titles);
        List<CharacterEntity> entities = this.characterRepository.findAll(this.characterSpecification.getByFilters(filtersDTO));
        List<CharacterDTO> dtos = this.characterMapper.characterEntitySet2DTOList(entities,true);
        return dtos;
    }

    // Invokes a mapper method and modifies Character values only
    public CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO)
    {
        Optional<CharacterEntity>entity = characterRepository.findById(id);
        if(!entity.isPresent())
        {
            throw new ParamNotFound("Character ID to modify not found");
        }
        this.characterMapper.modifyCharacterValues(entity.get(),characterDTO);
        CharacterEntity savedEntity = this.characterRepository.save(entity.get());
        CharacterDTO result = this.characterMapper.characterEntity2DTO(savedEntity, true);

        return result;
    }

    // Returns all Characters saved in Repository with them associated titles
    public List<CharacterDTO> getCharacters()
    {
        List<CharacterEntity> entities = characterRepository.findAll();
        List<CharacterDTO>result = characterMapper.characterEntitySet2DTOList(entities,true);
        return result;
    }

    // Soft Delete of a Character saved in Repository
    public void delete(Long id)
    {
        Optional<CharacterEntity>entity = this.characterRepository.findById(id);
        if(!entity.isPresent())
        {
            throw new ParamNotFound("Character ID not found, failed to delete");
        }
        this.characterRepository.deleteById(id);
    }


}
