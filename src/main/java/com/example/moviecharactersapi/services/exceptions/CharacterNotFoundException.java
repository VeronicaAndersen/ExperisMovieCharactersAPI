package com.example.moviecharactersapi.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CharacterNotFoundException extends RuntimeException{
  public CharacterNotFoundException(int id) {
    super("No professor exists with id: " + id);
  }
}