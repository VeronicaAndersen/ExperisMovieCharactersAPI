package com.example.moviecharactersapi.services.character;

import com.example.moviecharactersapi.services.CrudService;

public interface CharacterService extends CrudService<Character, Integer> {

  boolean exists(int id);
}
