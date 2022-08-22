package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.services.character.CharacterService;
import com.example.moviecharactersapi.services.movie.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

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
  @GetMapping("/{id}")
  public ResponseEntity findById(@PathVariable int id) {
    return ResponseEntity.ok(movieService.findById(id));
  }

  @PostMapping
  public ResponseEntity add(@RequestBody Movie movie) {
    Movie newProf = movieService.add(movie);
    URI uri = URI.create("movie/" + newProf.getId());
    return ResponseEntity.created(uri).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody Movie movie, @PathVariable int id) {
    if(movie.getId() != id)
      return ResponseEntity.badRequest().build();
    movieService.update(movie);
    return ResponseEntity.noContent().build();
  }

}
