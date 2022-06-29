package eu.janherzog.csgodatabase.database.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Skin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String description;
    private String lore;
    private Date dateAdded;
    private boolean canBeSouvenir;
    private boolean canBeStattrak;

    @Column(columnDefinition = "TEXT")
    private String image;

    @ManyToOne
    private Collection collection;
    @ManyToOne
    private Rarity rarity;
    @ManyToOne
    private WeaponType weaponType;
    @ManyToOne
    private Crate crate;
    @ManyToOne
    private Weapon weapon;
}
