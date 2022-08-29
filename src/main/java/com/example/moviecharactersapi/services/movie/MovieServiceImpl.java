package com.example.moviecharactersapi.services.movie;

import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.repositories.MovieRepository;
import com.example.moviecharactersapi.services.exceptions.CharacterNotFoundException;
import com.example.moviecharactersapi.services.exceptions.FranchiseNotFoundException;
import com.example.moviecharactersapi.services.exceptions.MovieNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class MovieServiceImpl implements MovieService{
  private final MovieRepository movieRepository;

  public MovieServiceImpl(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  @Override
  public Movie findById(Integer id) {
    return movieRepository.findById(id).orElseThrow(()-> new MovieNotFoundException(id));
  }

  @Override
  public Collection<Movie> findAll() {
    return movieRepository.findAll();
  }

  @Override
  public Movie add(Movie entity) {
    return movieRepository.save(entity);
  }

  @Override
  public Movie update(Movie entity) {
    return movieRepository.save(entity);
  }



  @Override
  @Transactional
  public void deleteById(Integer id) throws MovieNotFoundException{
    if(movieRepository.findById(id).isEmpty()) throw new MovieNotFoundException(id);
    movieRepository.deleteById(id);
  }

  @Override
  public boolean exists(int id) {
    return movieRepository.existsById(id);
  }
}
