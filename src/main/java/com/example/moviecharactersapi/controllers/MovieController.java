package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.services.character.CharacterService;
import com.example.moviecharactersapi.services.movie.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/movie")
public class MovieController {

  private final MovieService movieService;

  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping
  public ResponseEntity findAll() {
    return ResponseEntity.ok(movieService.findAll());
  }
}
