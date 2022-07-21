package com.alkemy.disney.disney.services.impl;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entities.GenreEntity;
import com.alkemy.disney.disney.entities.TitleEntity;
import com.alkemy.disney.disney.exceptions.ParamNotFound;
import com.alkemy.disney.disney.mappers.GenreMapper;
import com.alkemy.disney.disney.repositories.GenreRepository;
import com.alkemy.disney.disney.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    // Soft Delete of a Genre saved in Repository
    public void delete(Long id)
    {
        Optional<GenreEntity> entity = this.genreRepository.findById(id);
        if(!entity.isPresent())
        {
            throw new ParamNotFound("Genre ID not found, failed to delete");
        }
        this.genreRepository.deleteById(id);
    }
}
