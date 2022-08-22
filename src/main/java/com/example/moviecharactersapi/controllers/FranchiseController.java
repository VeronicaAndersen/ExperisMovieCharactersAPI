package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.services.character.CharacterService;
import com.example.moviecharactersapi.services.franchise.FranchiseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(path = "api/v1/franchise")
public class FranchiseController {

  private final FranchiseService franchiseService;

  public FranchiseController(FranchiseService franchiseService) {
    this.franchiseService = franchiseService;
  }

  @GetMapping
  public ResponseEntity findAll() {
    return ResponseEntity.ok(franchiseService.findAll());
  }
  @GetMapping("/{id}")
  public ResponseEntity findById(@PathVariable int id) {
    return ResponseEntity.ok(franchiseService.findById(id));
  }
  @PostMapping
  public ResponseEntity add(@RequestBody Franchise franc) {
    Franchise newProf = franchiseService.add(franc);
    URI uri = URI.create("franchise/" + newProf.getId());
    return ResponseEntity.created(uri).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody Franchise franc, @PathVariable int id) {
    if(franc.getId() != id)
      return ResponseEntity.badRequest().build();
    franchiseService.update(franc);
    return ResponseEntity.noContent().build();
  }
}
