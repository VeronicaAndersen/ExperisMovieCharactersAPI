package com.example.moviecharactersapi.services.franchise;

import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.services.CrudService;

public interface FranchiseService extends CrudService<Franchise, Integer> {

  boolean exists(int id);
}
