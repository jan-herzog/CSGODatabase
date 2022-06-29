package eu.janherzog.csgodatabase.database.repository;

import eu.janherzog.csgodatabase.database.model.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

public interface WeaponRepository extends JpaRepository<Weapon, Long> {

    Weapon findByName(String name);


}
