package com.alkemy.disney.disney.services.impl;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entities.GenreEntity;
import com.alkemy.disney.disney.mappers.GenreMapper;
import com.alkemy.disney.disney.repositories.GenreRepository;
import com.alkemy.disney.disney.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreMapper genreMapper;

    @Autowired
    private GenreRepository genreRepository;

    // Saves a Genre in Repository
    public GenreDTO save(GenreDTO dto)
    {
        GenreEntity entity = genreMapper.genreDTO2Entity(dto);
        GenreEntity savedEntity = genreRepository.save(entity);
        GenreDTO result = genreMapper.genreEntity2DTO(savedEntity);
        return result;
    }

    // Returns all Genres saved in Repository
    public List<GenreDTO> getGenres()
    {
        List<GenreEntity> entities = genreRepository.findAll();
        List<GenreDTO>result = genreMapper.genreEntityList2DTOList(entities);
        return result;
    }
}
