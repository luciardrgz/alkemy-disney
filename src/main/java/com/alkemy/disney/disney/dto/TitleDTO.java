package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class TitleDTO {
    private Long id;

    private String image;

    private String name;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate creationDate;

    private Integer score;

    private Long genreId;

    private List<CharacterDTO> characters;


}
