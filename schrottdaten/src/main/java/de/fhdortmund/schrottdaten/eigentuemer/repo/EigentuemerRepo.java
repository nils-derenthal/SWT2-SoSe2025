package de.fhdortmund.schrottdaten.eigentuemer.repo;

import de.fhdortmund.schrottdaten.eigentuemer.entity.Eigentuemer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EigentuemerRepo extends JpaRepository<Eigentuemer, Long> {
}