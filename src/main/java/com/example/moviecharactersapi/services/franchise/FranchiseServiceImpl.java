package com.example.moviecharactersapi.services.franchise;

import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.repositories.FranchiseRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FranchiseServiceImpl implements FranchiseService{

  private final FranchiseRepository franchiseRepository;

  public FranchiseServiceImpl(FranchiseRepository franchiseRepository) {
    this.franchiseRepository = franchiseRepository;
  }

  @Override
  public Franchise findById(Integer id) {
    return franchiseRepository.findById(id).get();
  }

  @Override
  public Collection<Franchise> findAll() {
    return franchiseRepository.findAll();
  }

  @Override
  public Franchise add(Franchise entity) {
    return null;
  }

  @Override
  public Franchise update(Franchise entity) {
    return null;
  }



  @Override
  public void deleteById(Integer integer) {

  }

  @Override
  public boolean exists(int id) {
    return false;
  }
}
