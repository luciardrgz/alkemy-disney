package com.alkemy.disney.disney.controllers;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("characters")
public class CharacterController {

    @Lazy
    @Autowired
    private CharacterService characterService;

    @PostMapping // Invokes the Character Service to save a created Character
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO characterDTO, @RequestParam Long titleId)
    {
        CharacterDTO savedCharacter = characterService.save(characterDTO, titleId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCharacter);
    }
}
