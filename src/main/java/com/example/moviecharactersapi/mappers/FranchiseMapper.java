package com.example.moviecharactersapi.mappers;


import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.models.dtos.franchise.FranchiseDTO;
import com.example.moviecharactersapi.models.dtos.movieCharacter.MovieCharacterDTO;
import com.example.moviecharactersapi.services.movie.MovieService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public abstract class FranchiseMapper {
    @Autowired
    private MovieService movieService;
    @Mapping(target="movies", source="movies", qualifiedByName = "mapMoviesToDto")
    public abstract FranchiseDTO franToFranDTO(Franchise franchise);
    @Mapping(target="movies", source="movies")
    public abstract Collection<FranchiseDTO> franToMovDTO(Collection<Franchise> franchises);
    @Mapping(target="movies", source="movies", qualifiedByName = "mapMoviesFromDTO")
    public abstract Franchise franDTOToMovie(FranchiseDTO franchiseDTO);
    @Named("mapMoviesToDto")
    Set<Integer> mapMoviesToDTO(Set<Movie> movies){
        if (movies == null) return null;
        return movies.stream().map(m -> m.getId()).collect(Collectors.toSet());
    }

    @Named("mapMoviesFromDTO")
    Set<Movie> mapMoviesFromDTO(Set<Integer> movieIds){
        if (movieIds == null) return null;
        return movieIds.stream().map(i -> movieService.findById(i)).collect(Collectors.toSet());
    }

}
