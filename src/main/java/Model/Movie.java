package Model;

import javax.persistence.*;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "character_id")
    private int id;

    @Column(name = "movie_title")
    private String title;

    @Column(name = "movie_genre")
    private String genre;

    @Column(name = "movie_release_year")
    private String release_year;

    @Column(name = "movie_director")
    private String director;

    @Column(name = "movie_picture")
    private String picture;

    @Column(name = "movie_trailer")
    private String trailer;
}
