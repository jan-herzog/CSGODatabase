package eu.janherzog.csgodatabase.database.repository;

import eu.janherzog.csgodatabase.database.model.Rarity;
import eu.janherzog.csgodatabase.database.model.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RarityRepository extends JpaRepository<Rarity, Long> {



}
