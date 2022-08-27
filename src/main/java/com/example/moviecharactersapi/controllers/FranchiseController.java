package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.mappers.FranchiseMapper;
import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.models.dtos.franchise.FranchiseDTO;
import com.example.moviecharactersapi.services.franchise.FranchiseService;
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
@RequestMapping(path = "api/v1/franchise")
public class FranchiseController {

  private final FranchiseService franchiseService;
  private final FranchiseMapper franchiseMapper;

  public FranchiseController(FranchiseService franchiseService, FranchiseMapper franchiseMapper) {
    this.franchiseService = franchiseService;
    this.franchiseMapper = franchiseMapper;
  }

  /**
   * Gets all franchises.
   *
   * @return Ok response.
   */
  @Operation(summary = "Get all franchises.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200",
                  description = "Success",
                  content = {
                          @Content(
                                  mediaType = "application/json",
                                  array = @ArraySchema(schema = @Schema(implementation = FranchiseDTO.class)))}),
          @ApiResponse(responseCode = "404",
                  description = "Franchise does not exist with supplied ID",
                  content = @Content)
  })

  @GetMapping
  public ResponseEntity<Collection<FranchiseDTO>> findAll() {
    Collection<FranchiseDTO> franchiseDTOS = franchiseMapper.franToMovDTO(franchiseService.findAll());
    return ResponseEntity.ok(franchiseDTOS);
  }

  /**
   * Gets franchises with specified ID.
   *
   * @param id Franchises ID to search for.
   * @return Ok response.
   */
  @Operation(summary = "Get a franchise by ID.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200",
                  description = "Success",
                  content = {@Content(mediaType = "application/json",
                          schema = @Schema(implementation = FranchiseDTO.class))}),
          @ApiResponse(responseCode = "404",
                  description = "Franchise does not exist with supplied ID",
                  content = @Content)
  })
  @GetMapping("/{id}")
  public ResponseEntity<FranchiseDTO> findById(@PathVariable int id) {
    FranchiseDTO franchiseDTO = franchiseMapper.franToFranDTO(franchiseService.findById(id));
    return ResponseEntity.ok(franchiseDTO);
  }

  /**
   * Post operation which adds a new franchise to the database.
   *
   * @param franchise Franchise that adds to database.
   * @return Created response.
   */
  @Operation(summary = "Adds a franchise.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201",
                  description = "Created",
                  content = {
                          @Content(
                                  mediaType = "application/json",
                                  array = @ArraySchema(schema = @Schema(implementation = FranchiseDTO.class)))}),
          @ApiResponse(responseCode = "400",
                  description = "Invalid ID supplied",
                  content = @Content)
  })
  @PostMapping
  public ResponseEntity add(@RequestBody Franchise franchise) {
    if (franchiseService.exists(franchise.getId())) return ResponseEntity.badRequest().build();
    Franchise newFranchise = franchiseService.add(franchise);
    URI uri = URI.create("franchise/" + newFranchise.getId());
    return ResponseEntity.created(uri).build();
  }

  /**
   * Update operation which updates movie character with specified ID and given body.
   *
   * @param franchiseDTO body which stores the updated properties.
   * @param id ID of franchise which should be updated.
   * @return Response with no content.
   */
  @Operation(summary = "Updates a franchise.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "204",
                  description = "Franchise successfully updated",
                  content = @Content),
          @ApiResponse(responseCode = "400",
                  description = "Malformed request",
                  content = @Content),
          @ApiResponse(responseCode = "404",
                  description = "Franchise not found with supplied ID",
                  content = @Content)
  })
  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody FranchiseDTO franchiseDTO, @PathVariable int id) {
    if (franchiseDTO.getId() != id)
      return ResponseEntity.badRequest().build();
    franchiseService.update(franchiseMapper.franDTOToMovie(franchiseDTO));
    return ResponseEntity.noContent().build();
  }

  /**
   * Delete operation which deletes franchise by specified ID.
   *
   * @param id Franchise with specified ID to delete.
   * @return Response with no content.
   */
  @Operation(summary = "Deletes a franchise.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "204",
                  description = "Franchise successfully deleted",
                  content = @Content),
          @ApiResponse(responseCode = "400",
                  description = "Malformed request",
                  content = @Content),
          @ApiResponse(responseCode = "404",
                  description = "Franchise not found with supplied ID",
                  content = @Content)
  })
  @DeleteMapping("/{id}")
  public ResponseEntity deleteById(@RequestBody Franchise franchise, @PathVariable int id) {
    if (franchise.getId() != id)
      return ResponseEntity.badRequest().build();
    franchiseService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
