package eu.janherzog.csgodatabase.database.repository;

import eu.janherzog.csgodatabase.database.model.Collection;
import eu.janherzog.csgodatabase.database.model.Crate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection, Long> {



}
