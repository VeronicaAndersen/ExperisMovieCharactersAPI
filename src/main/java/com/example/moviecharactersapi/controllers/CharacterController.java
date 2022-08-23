package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.Movie;
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

  @GetMapping("/{id}")
  public ResponseEntity findById(@PathVariable int id) {
    return ResponseEntity.ok(characterService.findById(id));
  }
  @PostMapping
  public ResponseEntity add(@RequestBody MovieCharacter character) {
    MovieCharacter newCharacter = characterService.add(character);
    URI uri = URI.create("moviecharacters/" + newCharacter.getId());
    return ResponseEntity.created(uri).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody MovieCharacter character, @PathVariable int id) {
    if(character.getId() != id)
      return ResponseEntity.badRequest().build();
    characterService.update(character);
    return ResponseEntity.noContent().build();
  }
@DeleteMapping("/{id}")
  public ResponseEntity deleteById(@RequestBody MovieCharacter character, @PathVariable int id) {
    if(character.getId() != id)
      return ResponseEntity.badRequest().build();
    characterService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}