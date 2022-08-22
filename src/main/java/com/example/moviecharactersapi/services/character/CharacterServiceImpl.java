package com.example.moviecharactersapi.services.character;

import com.example.moviecharactersapi.models.MovieCharacter;
import com.example.moviecharactersapi.repositories.CharacterRepository;
import com.example.moviecharactersapi.services.exceptions.CharacterNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
  @Service
  public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository) {
      this.characterRepository = characterRepository;
    }

    @Override
    public MovieCharacter findById(Integer id) {
      return characterRepository.findById(id).get();
    }

    @Override
    public Collection<MovieCharacter> findAll() {
      return characterRepository.findAll();
    }

    @Override
    public MovieCharacter add(MovieCharacter entity) {
      return characterRepository.save(entity);
    }

    @Override
    public MovieCharacter update(MovieCharacter entity) {
      return characterRepository.save(entity);
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public boolean exists(int id) {
      return false;
    }
  }