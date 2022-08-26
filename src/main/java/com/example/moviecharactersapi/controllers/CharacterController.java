package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.mappers.MovieCharacterMapper;
import com.example.moviecharactersapi.models.MovieCharacter;
import com.example.moviecharactersapi.models.dtos.movieCharacter.MovieCharacterDTO;
import com.example.moviecharactersapi.services.character.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "api/v1/moviecharacters")
public class CharacterController {
  private final CharacterService characterService;

  private final MovieCharacterMapper movieCharacterMapper;

  public CharacterController(CharacterService characterService, MovieCharacterMapper movieCharacterMapper) {
    this.characterService = characterService;
    this.movieCharacterMapper = movieCharacterMapper;
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
    if(characterService.exists(character.getId())) return ResponseEntity.badRequest().build();
    MovieCharacter newCharacter = characterService.add(character);
    URI uri = URI.create("moviecharacters/" + newCharacter.getId());
    return ResponseEntity.created(uri).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody MovieCharacterDTO movieCharacterDTO, @PathVariable int id) {
    if(movieCharacterDTO.getId() != id) return ResponseEntity.badRequest().build();
    characterService.update(movieCharacterMapper.characterDTOToMovie(movieCharacterDTO));
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