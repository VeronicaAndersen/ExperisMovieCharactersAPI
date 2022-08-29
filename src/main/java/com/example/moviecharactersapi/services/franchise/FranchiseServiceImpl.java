package com.example.moviecharactersapi.services.franchise;

import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.repositories.FranchiseRepository;
import com.example.moviecharactersapi.services.exceptions.FranchiseNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FranchiseServiceImpl implements FranchiseService {

  private final FranchiseRepository franchiseRepository;

  public FranchiseServiceImpl(FranchiseRepository franchiseRepository) {
    this.franchiseRepository = franchiseRepository;
  }

  @Override
  public Franchise findById(Integer id) {
    return franchiseRepository.findById(id).orElseThrow(() -> new FranchiseNotFoundException(id));
  }

  @Override
  public Collection<Franchise> findAll() {
    return franchiseRepository.findAll();
  }

  @Override
  public Franchise add(Franchise entity) {
    return franchiseRepository.save(entity);
  }

  @Override
  public Franchise update(Franchise entity) {
    return franchiseRepository.save(entity);
  }

  @Override
  public void deleteById(Integer id) throws FranchiseNotFoundException {
    if (franchiseRepository.findById(id).isEmpty()) throw new FranchiseNotFoundException(id);
    Franchise franchise = franchiseRepository.findById(id).get();
    franchise.getMovies().forEach(m -> m.setFranchise(null));
    franchiseRepository.deleteById(id);
  }

  @Override
  public boolean exists(int id) {
    return franchiseRepository.existsById(id);
  }
}
