package com.example.moviecharactersapi.mappers;

import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.models.dtos.franchise.FranchiseDTO;
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

    /**
     * Maps Franchise to FranchiseDTO.
     * @param franchise Franchise to map.
     * @return mapped FranchiseDTO.
     */
    @Mapping(target="movies", source="movies", qualifiedByName = "mapMoviesToDto")
    public abstract FranchiseDTO franToFranDTO(Franchise franchise);

    /**
     * Maps Franchise to MovieDTO.
     * @param franchises Franchise to map.
     * @return mapped MovieDTO.
     */
    @Mapping(target="movies", source="movies")
    public abstract Collection<FranchiseDTO> franToMovDTO(Collection<Franchise> franchises);

    /**
     * Maps FranchiseDTO to Movie.
     * @param franchiseDTO FranchiseDTO to map.
     * @return mapped Movies.
     */
    @Mapping(target="movies", source="movies", qualifiedByName = "mapMoviesFromDTO")
    public abstract Franchise franDTOToMovie(FranchiseDTO franchiseDTO);

    /**
     * Maps Movies to DTO.
     * @param movies
     * @return movies.
     */
    @Named("mapMoviesToDto")
    Set<Integer> mapMoviesToDTO(Set<Movie> movies){
        if (movies == null) return null;
        return movies.stream().map(m -> m.getId()).collect(Collectors.toSet());
    }

    /**
     * Maps Movies from DTO.
     * @param movieIds
     * @return movieIds.
     */
    @Named("mapMoviesFromDTO")
    Set<Movie> mapMoviesFromDTO(Set<Integer> movieIds){
        if (movieIds == null) return null;
        return movieIds.stream().map(i -> movieService.findById(i)).collect(Collectors.toSet());
    }

}
