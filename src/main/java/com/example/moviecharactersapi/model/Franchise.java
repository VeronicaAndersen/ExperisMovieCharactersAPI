package com.example.moviecharactersapi.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Franchise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "franchise_id")
    private int id;

    @Column(name = "franchise_name", length = 50, nullable = false)
    private String name;

    @Column(name = "franchise_description",  length = 100)
    private String description;

    @OneToMany(mappedBy = "franchise")
    private Set<Movie> movie;
}
