package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.services.franchise.FranchiseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/franchise")
public class FranchiseController {

  private final FranchiseService franchiseService;

  public FranchiseController(FranchiseService franchiseService) {
    this.franchiseService = franchiseService;
  }

  @GetMapping
  public ResponseEntity<Collection<Franchise>> findAll() {
    return ResponseEntity.ok(franchiseService.findAll());
  }
  @GetMapping("/{id}")
  public ResponseEntity findById(@PathVariable int id) {
    return ResponseEntity.ok(franchiseService.findById(id));
  }
  @PostMapping
  public ResponseEntity add(@RequestBody Franchise franchise) {
    if(franchiseService.exists(franchise.getId())) return ResponseEntity.badRequest().build();
    Franchise newFranchise = franchiseService.add(franchise);
    URI uri = URI.create("franchise/" + newFranchise.getId());
    return ResponseEntity.created(uri).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody Franchise franchise, @PathVariable int id) {
    if(franchise.getId() != id)
      return ResponseEntity.badRequest().build();
    franchiseService.update(franchise);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteById(@RequestBody Franchise franchise, @PathVariable int id) {
    if(franchise.getId() != id)
      return ResponseEntity.badRequest().build();
    franchiseService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
