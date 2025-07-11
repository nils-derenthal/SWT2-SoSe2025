package de.fhdortmund.schrottdaten.immobilie.repo;

import de.fhdortmund.schrottdaten.immobilie.entity.Koordinaten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KoordinatenRepo extends JpaRepository<Koordinaten, Long> {
}
