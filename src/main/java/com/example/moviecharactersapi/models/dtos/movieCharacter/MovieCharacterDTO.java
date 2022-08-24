package com.example.moviecharactersapi.models.dtos.movieCharacter;

import lombok.Data;

import java.util.Set;

@Data
public class MovieCharacterDTO {
    private int id;
    private String name;
    private String alias;
    private String gender;
    private String picture;
    private Set<Integer> movies;
}
