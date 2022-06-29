package eu.janherzog.csgodatabase.database.repository;

import eu.janherzog.csgodatabase.database.model.Collection;
import eu.janherzog.csgodatabase.database.model.Crate;
import eu.janherzog.csgodatabase.database.model.Skin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkinRepository extends JpaRepository<Skin, Long> {
    List<Skin> findByCollection(Collection collection);

    List<Skin> findByCrate(Crate crate);




}
