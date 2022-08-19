package com.example.moviecharactersapi.services.character;

import com.example.moviecharactersapi.models.MovieCharacter;
import com.example.moviecharactersapi.services.CrudService;

public interface CharacterService extends CrudService<MovieCharacter, Integer> {

  boolean exists(int id);
}
