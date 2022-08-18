package com.example.moviecharactersapi.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "character_id")
    private int id;

    @Column(name = "movie_title", length = 80, nullable = false)
    private String title;

    @Column(name = "movie_genre" , length = 100, nullable = false)
    private String genre;

    @Column(name = "movie_release_year", length = 4, nullable = false)
    private String release_year;

    @Column(name = "movie_director", length = 50, nullable = false)
    private String director;

    @Column(name = "movie_picture", length = 100)
    private String picture;

    @Column(name = "movie_trailer", length = 200)
    private String trailer;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @ManyToMany
    @JoinTable(
            name = "movie_character",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "character_id")}
    )
    private Set<Character> characters;
}
