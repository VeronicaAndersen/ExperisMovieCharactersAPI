package com.example.moviecharactersapi.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Franchise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "franchise_id")
    private int id;

    @Column(name = "franchise_name", length = 50, nullable = false)
    private String name;

    @Column(name = "franchise_description",  length = 600)
    private String description;

    @OneToMany(mappedBy = "franchise")
    private Set<Movie> movies;

}
