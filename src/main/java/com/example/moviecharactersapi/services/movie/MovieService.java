package com.example.moviecharactersapi.services.movie;

import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.services.CrudService;

public interface MovieService extends CrudService<Movie, Integer> {

  boolean exists(int id);
}

