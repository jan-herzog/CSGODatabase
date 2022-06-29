package eu.janherzog.csgodatabase.database.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DropRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double dropRate;

    @ManyToOne
    private Collection collection;
    @ManyToOne
    private Crate crate;

}
