package Model;

import javax.persistence.*;

@Entity
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "character_id")
    private int id;

    @Column(name = "character_name")
    private String name;

    @Column(name = "character_alias")
    private String alias;

    @Column(name = "character_gender")
    private String gender;

    @Column(name = "character_picture")
    private String picture;
}
