package com.example.moviecharactersapi.mappers;

import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.models.MovieCharacter;
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
public abstract class MovieCharacterMapper {
    @Autowired
    private MovieService movieService;

    /**
     * Maps Movies to MovieCharacterDTO.
     * @param character MovieCharacter to map.
     * @return mapped Movies.
     */
    @Mapping(target="movies", source="movies", qualifiedByName = "moviesToIds")
    public abstract MovieCharacterDTO movieToCharDTO(MovieCharacter character);

    /**
     * Maps Movies to MovieCharacterDTO
     * @param characters MovieCharacter to map.
     * @return mapped Movies.
     */
    @Mapping(target="movies", source="movies")
    public abstract Collection<MovieCharacterDTO> movieToCharDTO(Collection<MovieCharacter> characters);

    /**
     * Maps MovieCharacterDTO to Movies.
     * @param characterDTO MovieCharacterDTO to map.
     * @return mapped MovieCharacterDTO.
     */
    @Mapping(target="movies", source="movies", qualifiedByName = "mapToMovieCharacters")
    public abstract MovieCharacter characterDTOToMovie(MovieCharacterDTO characterDTO);

    /**
     * Maps to MovieCharacters
     * @param movieIds
     * @return movieIds.
     */
    @Named("mapToMovieCharacters")
    Set<Movie>mapToMovieCharacters(Set<Integer> movieIds){

        return movieIds.stream()
                .map(i -> movieService.findById(i))
                .collect(Collectors.toSet());
    }

    /**
     * Maps Movies to Ids.
     * @param movies
     * @return movies.
     */
    @Named("moviesToIds")
    Set<Integer> map(Set<Movie> movies){
        if (movies == null) return null;
        return movies.stream()
                .map(s -> s.getId()).collect(Collectors.toSet());
    }

}
