package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.services.character.CharacterService;
import com.example.moviecharactersapi.services.franchise.FranchiseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
