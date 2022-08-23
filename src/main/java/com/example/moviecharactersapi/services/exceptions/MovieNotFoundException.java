package com.example.moviecharactersapi.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends RuntimeException{

  public MovieNotFoundException(int id) {
    super("No movie exists with id: " + id);
  }
}