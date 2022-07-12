package com.alkemy.disney.disney.controllers;

import com.alkemy.disney.disney.dto.TitleDTO;
import com.alkemy.disney.disney.services.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("titles") // endpoint
public class TitleController {

    @Autowired
    private TitleService titleService;

    @PostMapping
    public ResponseEntity<TitleDTO> save(@RequestBody TitleDTO title){
        TitleDTO savedTitle = titleService.save(title);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTitle);
    }
}
