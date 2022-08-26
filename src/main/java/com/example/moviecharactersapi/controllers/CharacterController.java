package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.mappers.MovieCharacterMapper;
import com.example.moviecharactersapi.models.MovieCharacter;
import com.example.moviecharactersapi.models.dtos.movieCharacter.MovieCharacterDTO;
import com.example.moviecharactersapi.services.character.CharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/moviecharacters")
public class CharacterController {
  private final CharacterService characterService;

  private final MovieCharacterMapper movieCharacterMapper;

  public CharacterController(CharacterService characterService, MovieCharacterMapper movieCharacterMapper) {
    this.characterService = characterService;
    this.movieCharacterMapper = movieCharacterMapper;
  }

  @Operation(summary = "Get all movie characters.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200",
                  description = "Success",
                  content = {
                          @Content(
                                  mediaType = "application/json",
                                  array = @ArraySchema(schema = @Schema(implementation = MovieCharacterDTO.class)))}),
          @ApiResponse(responseCode = "404",
                  description = "Movie character does not exist with supplied ID.",
                  content = @Content)
  })

  @GetMapping
  public ResponseEntity<Collection<MovieCharacterDTO>> findAll() {
    Collection<MovieCharacterDTO> charactersDto = movieCharacterMapper.movieToCharDTO(characterService.findAll());
    return ResponseEntity.ok(charactersDto);
  }

  @Operation(summary = "Get a movie character by ID.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200",
                  description = "Success",
                  content = {@Content(mediaType = "application/json",
                          schema = @Schema(implementation = MovieCharacterDTO.class))}),
          @ApiResponse(responseCode = "404",
                  description = "Movie character does not exist with supplied ID.",
                  content = @Content)
  })

  @GetMapping("/{id}")
  public ResponseEntity<MovieCharacterDTO> findById(@PathVariable int id) {
    MovieCharacterDTO characterDTO = movieCharacterMapper.movieToCharDTO(characterService.findById(id));
    return ResponseEntity.ok(characterDTO);
  }

  @Operation(summary = "Adds a movie character.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200",
                  description = "Success",
                  content = {@Content(mediaType = "application/json",
                          schema = @Schema(implementation = MovieCharacterDTO.class))}),
          @ApiResponse(responseCode = "404",
                  description = "Movie character is invalid ID.",
                  content = @Content)
  })
  @PostMapping
  public ResponseEntity add(@RequestBody MovieCharacter character) {
    if (characterService.exists(character.getId())) return ResponseEntity.badRequest().build();
    MovieCharacter newCharacter = characterService.add(character);
    URI uri = URI.create("moviecharacters/" + newCharacter.getId());
    return ResponseEntity.created(uri).build();
  }

  @Operation(summary = "Updates a movie character.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "204",
                  description = "Movie character successfully updated.",
                  content = @Content),
          @ApiResponse(responseCode = "400",
                  description = "Malformed request.",
                  content = @Content),
          @ApiResponse(responseCode = "404",
                  description = "Movie character not found with supplied ID.",
                  content = @Content)
  })

  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody MovieCharacterDTO movieCharacterDTO, @PathVariable int id) {
    if (movieCharacterDTO.getId() != id) return ResponseEntity.badRequest().build();
    characterService.update(movieCharacterMapper.characterDTOToMovie(movieCharacterDTO));
    return ResponseEntity.noContent().build();
  }

  @Operation(summary = "Deletes a movie character.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "204",
                  description = "Movie character successfully deleted.",
                  content = @Content),
          @ApiResponse(responseCode = "400",
                  description = "Malformed request.",
                  content = @Content),
          @ApiResponse(responseCode = "404",
                  description = "Movie character not found with supplied ID.",
                  content = @Content)
  })

  @DeleteMapping("/{id}")
  public ResponseEntity deleteById(@PathVariable int id) {
    characterService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}