package com.example.moviecharactersapi.services.movie;

import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MovieServiceImpl implements MovieService{
  private final MovieRepository movieRepository;

  public MovieServiceImpl(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  @Override
  public Movie findById(Integer id) {
    return movieRepository.findById(id).get();
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
  public void deleteById(Integer integer) {

  }

  @Override
  public boolean exists(int id) {
    return false;
  }
}
