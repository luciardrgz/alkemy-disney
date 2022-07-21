package com.alkemy.disney.disney.controllers;

import com.alkemy.disney.disney.dto.TitleDTO;
import com.alkemy.disney.disney.services.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("titles")
public class TitleController {

    @Lazy
    @Autowired
    private TitleService titleService; // Invoked by Controller methods

    @PostMapping // Saves a created Title
    public ResponseEntity<TitleDTO> save(@RequestBody TitleDTO title){
        TitleDTO savedTitle = titleService.save(title);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTitle);
    }

    @GetMapping("/id") // Retrieves a Title by its saved id in Repository
    public ResponseEntity<TitleDTO>getById(@PathVariable Long id)
    {
        TitleDTO title = this.titleService.getTitleDTOById(id);
        return ResponseEntity.ok(title);
    }

    @GetMapping // Searches a Title with filters
    public ResponseEntity<List<TitleDTO>>search(@RequestParam(required = false) String name,
                                          @RequestParam(required = false) Long genreId,
                                          @RequestParam(required = false, defaultValue = "ASC") String order)
    {
        List<TitleDTO>titles = this.titleService.getByFilters(name,genreId,order);
        return ResponseEntity.ok().body(titles);
    }


}
