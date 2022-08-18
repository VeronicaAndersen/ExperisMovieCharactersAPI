package Model;

import javax.persistence.*;

@Entity
public class Franchise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "franchise_id")
    private int id;

    @Column(name = "franchise_name")
    private String name;

    @Column(name = "franchise_description")
    private String description;
}
