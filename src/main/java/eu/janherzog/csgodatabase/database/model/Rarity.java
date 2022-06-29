package eu.janherzog.csgodatabase.database.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rarity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    private Collection collection;

    @ManyToOne
    private Crate crate;

    @OneToOne
    private DropRate dropRate;

}
