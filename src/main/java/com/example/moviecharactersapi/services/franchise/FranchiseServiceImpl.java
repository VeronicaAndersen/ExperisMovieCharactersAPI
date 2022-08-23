package com.example.moviecharactersapi.services.franchise;

import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.models.MovieCharacter;
import com.example.moviecharactersapi.repositories.FranchiseRepository;
import com.example.moviecharactersapi.services.exceptions.CharacterNotFoundException;
import com.example.moviecharactersapi.services.exceptions.FranchiseNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    return franchiseRepository.save(entity);
  }

  @Override
  public Franchise update(Franchise entity) {
    return franchiseRepository.save(entity);
  }



  @Override
  @Transactional
  public void deleteById(Integer id) throws FranchiseNotFoundException {
    if(franchiseRepository.existsById(id)) {
      // Set relationships to null so we can delete without referential problems
      Franchise franchise = franchiseRepository.findById(id).get();
      franchise.getMovie().forEach(s -> s.setFranchise(null));
      franchiseRepository.delete(franchise);
    }
    else
      throw new FranchiseNotFoundException(id);
  }

  @Override
  public boolean exists(int id) {
    return false;
  }
}
