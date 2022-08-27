package com.example.moviecharactersapi.controllers;

import com.example.moviecharactersapi.mappers.MovieMapper;
import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.models.dtos.movie.MovieDTO;
import com.example.moviecharactersapi.services.movie.MovieService;
import com.example.moviecharactersapi.util.ApiErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/movie")
public class MovieController {

  private final MovieService movieService;
  private final MovieMapper movieMapper;

  public MovieController(MovieService movieService, MovieMapper movieMapper) {
    this.movieService = movieService;
    this.movieMapper = movieMapper;
  }

  /**
   * Gets all movies.
   *
   * @return Ok response.
   */
  @Operation(summary = "Get all movies.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200",
                  description = "Success",
                  content = {
                          @Content(
                                  mediaType = "application/json",
                                  array = @ArraySchema(schema = @Schema(implementation = MovieDTO.class)))}),
          @ApiResponse(responseCode = "404",
                  description = "Movie does not exist with supplied ID",
                  content = {
                          @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = ApiErrorResponse.class))})
  })
  @GetMapping
  public ResponseEntity<Collection<MovieDTO>> findAll() {
    Collection<MovieDTO> moviesDTO = movieMapper.movieToMovieDto(movieService.findAll());
    return ResponseEntity.ok(moviesDTO);
  }

  /**
   * Gets movie with specified ID.
   *
   * @param id Movie ID to search for.
   * @return Ok response.
   */
  @Operation(summary = "Get a movie by ID.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200",
                  description = "Success",
                  content = {@Content(mediaType = "application/json",
                          schema = @Schema(implementation = MovieDTO.class))}),
          @ApiResponse(responseCode = "404",
                  description = "Movie does not exist with supplied ID",
                  content = {@Content(mediaType = "application/json",
                          schema = @Schema(implementation = ApiErrorResponse.class))})
  })
  @GetMapping("/{id}")
  public ResponseEntity<MovieDTO> findById(@PathVariable int id) {
    MovieDTO movieDTO = movieMapper.movieToMovieDto(movieService.findById(id));
    return ResponseEntity.ok(movieDTO);
  }

  /**
   * Post operation which adds a new movie to the database.
   *
   * @param movie Movie that adds to database.
   * @return Created response.
   */
  @Operation(summary = "Adds a movie.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201",
                  description = "Created",
                  content = {
                          @Content(
                                  mediaType = "application/json",
                                  array = @ArraySchema(schema = @Schema(implementation = MovieDTO.class)))}),
          @ApiResponse(responseCode = "400",
                  description = "Invalid ID supplied")
  })

  @PostMapping
  public ResponseEntity add(@RequestBody Movie movie) {
    if (movieService.exists(movie.getId())) return ResponseEntity.badRequest().build();
    Movie newMovie = movieService.add(movie);
    URI uri = URI.create("movie/" + newMovie.getId());
    return ResponseEntity.created(uri).build();
  }

  /**
   * Update operation which updates movie character with specified ID and given body.
   *
   * @param movieDTO body which stores the updated properties.
   * @param id ID of movie which should be updated.
   * @return Response with no content.
   */
  @Operation(summary = "Updates a movie.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "204",
                  description = "Movie successfully updated",
                  content = @Content),
          @ApiResponse(responseCode = "400",
                  description = "Malformed request",
                  content = {@Content(mediaType = "application/json",
                          schema = @Schema(implementation = ErrorAttributeOptions.class))}),
          @ApiResponse(responseCode = "404",
                  description = "Movie not found with supplied ID",
                  content = @Content)
  })
  @PutMapping("/{id}")
  public ResponseEntity update(@RequestBody MovieDTO movieDTO, @PathVariable int id) {
    if (movieDTO.getId() != id)
      return ResponseEntity.badRequest().build();
    movieService.update(movieMapper.movieDtoToMovie(movieDTO));
    return ResponseEntity.noContent().build();
  }

  /**
   * Delete operation which deletes movie by specified ID.
   *
   * @param id Movie with specified ID to delete.
   * @return Response with no content.
   */
  @Operation(summary = "Deletes a movie.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "204",
                  description = "Movie successfully deleted",
                  content = @Content),
          @ApiResponse(responseCode = "400",
                  description = "Malformed request",
                  content = {@Content(mediaType = "application/json",
                          schema = @Schema(implementation = ErrorAttributeOptions.class))}),
          @ApiResponse(responseCode = "404",
                  description = "Movie not found with supplied ID",
                  content = @Content)
  })

  @DeleteMapping("/{id}")
  public ResponseEntity deleteById(@PathVariable int id) {
    movieService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
