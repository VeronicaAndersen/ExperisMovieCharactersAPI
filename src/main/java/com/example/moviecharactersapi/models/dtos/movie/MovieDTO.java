package com.example.moviecharactersapi.models.dtos.movie;

import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Set;

@Data
public class MovieDTO {
    private int id;
    private String title;
    private String genre;
    private int releaseYear;
    private String director;
    private String picture;
    private String trailer;
    private Set<Integer> movie_characters;
    private Integer franchise;
}
