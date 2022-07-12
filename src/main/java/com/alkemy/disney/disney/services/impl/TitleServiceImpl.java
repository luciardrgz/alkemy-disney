package com.alkemy.disney.disney.services.impl;

import com.alkemy.disney.disney.dto.TitleDTO;
import com.alkemy.disney.disney.entities.TitleEntity;
import com.alkemy.disney.disney.mappers.TitleMapper;
import com.alkemy.disney.disney.repositories.TitleRepository;
import com.alkemy.disney.disney.services.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TitleServiceImpl implements TitleService {

    @Autowired
    private TitleMapper titleMapper;

    @Autowired
    private TitleRepository titleRepository;

    public TitleDTO save(TitleDTO dto)
    {
        TitleEntity entity = titleMapper.titleDTO2Entity(dto);
        TitleEntity savedEntity = titleRepository.save(entity);
        TitleDTO result = titleMapper.titleEntity2DTO(savedEntity);
        return result;
    }
}
