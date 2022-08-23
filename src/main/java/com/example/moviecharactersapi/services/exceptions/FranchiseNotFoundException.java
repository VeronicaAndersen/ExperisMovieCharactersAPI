package com.example.moviecharactersapi.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public @ResponseStatus(value = HttpStatus.NOT_FOUND)
class FranchiseNotFoundException  extends RuntimeException{
  public FranchiseNotFoundException(int id) {
    super("No franchise exists with id: " + id);
  }
}
