package com.example.moviecharactersapi.services.character;

import com.example.moviecharactersapi.models.MovieCharacter;
import com.example.moviecharactersapi.repositories.CharacterRepository;
import com.example.moviecharactersapi.services.exceptions.CharacterNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
  @Transactional
  public void deleteById(Integer id) throws CharacterNotFoundException{
    if(characterRepository.findById(id).isEmpty()) throw new CharacterNotFoundException(id);
    MovieCharacter character = characterRepository.findById(id).get();
    character.getMovies().forEach(m -> m.getMovie_characters().remove(character));
    characterRepository.deleteById(id);
  }


  @Override
  public boolean exists(int id) {
    return characterRepository.existsById(id);
  }
  }