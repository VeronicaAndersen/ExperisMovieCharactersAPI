package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.MovieCharacter;
import com.example.moviecharactersapi.services.character.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "api/v1/moviecharacters")
public class CharacterController {
  private final CharacterService characterService;

  public CharacterController(CharacterService characterService) {
    this.characterService = characterService;
  }

  @GetMapping
  public ResponseEntity findAll() {
    return ResponseEntity.ok(characterService.findAll());
  }


}