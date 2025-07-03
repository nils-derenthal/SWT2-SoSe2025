package de.fhdortmund.schrottverwaltung.eigentuemer.Repository;

import de.fhdortmund.schrottverwaltung.eigentuemer.Eigentuemer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EigentuemerRepo extends JpaRepository<Eigentuemer, Long> {
}