package com.example.moviecharactersapi.mappers;

import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.models.MovieCharacter;
import com.example.moviecharactersapi.models.dtos.movie.MovieDTO;
import com.example.moviecharactersapi.services.character.CharacterService;
import com.example.moviecharactersapi.services.franchise.FranchiseService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class MovieMapper {
    @Autowired
    private CharacterService characterService;

    @Autowired
    private FranchiseService franchiseService;

    /**
     * Maps Movies to MovieDTO.
     * @param movie Movie to map.
     * @return mapped MovieDTO
     */
    @Mapping(target="franchise", source="franchise.id")
    @Mapping(target="movie_characters", source="movie_characters", qualifiedByName = "mapFromCharacters")
    public abstract MovieDTO movieToMovieDto(Movie movie);

    public abstract Collection<MovieDTO> movieToMovieDto(Collection<Movie> movies);

    /**
     * Maps MovieDTO too Movies.
     * @param movieDTO MovieDTO to map.
     * @return mapped Movies.
     */
    @Mapping(target="franchise", source="franchise", qualifiedByName = "mapToFranchise")
    @Mapping(target="movie_characters", source="movie_characters", qualifiedByName = "mapToCharacters")
    public abstract Movie movieDtoToMovie(MovieDTO movieDTO);

    /**
     * Maps to MovieCharacters.
     * @param characterIds
     * @return characterIds.
     */
    @Named("mapToCharacters")
    Set<MovieCharacter> mapToCharacters(Set<Integer> characterIds){
        if (characterIds == null) return null;
        return characterIds.stream().map(i -> characterService.findById(i)).collect(Collectors.toSet());
    }

    /**
     * Maps from MoveCharacters.
     * @param characters
     * @return characters.
     */
    @Named("mapFromCharacters")
    Set<Integer> mapFromCharacters(Set<MovieCharacter> characters){
        if (characters ==  null) return null;
        return characters.stream().map(MovieCharacter::getId).collect(Collectors.toSet());
    }

    /**
     * Maps from Franchise
     * @param franchise
     * @return franchise.
     */
    @Named("mapFromFranchise")
    Integer mapFromFranchise(Franchise franchise){
        if (franchise ==  null) return null;
        return franchise.getId();
    }

    /**
     * Maps to Franchise
     * @param id
     * @return id.
     */
    @Named("mapToFranchise")
    Franchise mapToFranchise(Integer id){
        if(id == null) return null;
        return franchiseService.findById(id);
    }

}
