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

    @Mapping(target="franchise", source="franchise", qualifiedByName = "mapFromFranchise")
    @Mapping(target="movie_characters", source="movie_characters", qualifiedByName = "mapFromCharacters")
    public abstract MovieDTO movieToMovieDto(Movie movie);

    public abstract Collection<MovieDTO> movieToMovieDto(Collection<Movie> movies);

    @Mapping(target="franchise", source="franchise", qualifiedByName = "mapToFranchise")
    @Mapping(target="movie_characters", source="movie_characters", qualifiedByName = "mapToCharacters")
    public abstract Movie movieDtoToMovie(MovieDTO movieDTO);

    @Named("mapToCharacters")
    Set<MovieCharacter> mapToCharacters(Set<Integer> characterIds){
        if (characterIds == null) return null;
        return characterIds.stream().map(i -> characterService.findById(i)).collect(Collectors.toSet());
    }

    @Named("mapFromCharacters")
    Set<Integer> mapFromCharacters(Set<MovieCharacter> characters){
        if (characters ==  null) return null;
        return characters.stream().map(MovieCharacter::getId).collect(Collectors.toSet());
    }

    @Named("mapFromFranchise")
    Integer mapFromFranchise(Franchise franchise){
        return franchise.getId();
    }

    @Named("mapToFranchise")
    Franchise mapToFranchise(Integer id){
        if(id == null) return null;
        return franchiseService.findById(id);
    }

}
