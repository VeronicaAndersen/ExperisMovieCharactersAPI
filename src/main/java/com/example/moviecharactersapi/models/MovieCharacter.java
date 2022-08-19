package com.example.moviecharactersapi.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class MovieCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "character_id")
    private int id;

    @Column(name = "character_name", length = 50, nullable = false)
    private String name;

    @Column(name = "character_alias", length = 50)
    private String alias;

    @Column(name = "character_gender", length = 15)
    private String gender;

    @Column(name = "character_picture", length = 200)
    private String picture;

    @ManyToMany(mappedBy = "movie_characters")
    private Set<Movie> movies;
}
