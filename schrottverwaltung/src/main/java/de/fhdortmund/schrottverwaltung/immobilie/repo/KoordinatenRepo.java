package de.fhdortmund.schrottverwaltung.immobilie.repo;

import de.fhdortmund.schrottverwaltung.immobilie.Koordinaten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KoordinatenRepo extends JpaRepository<Koordinaten, Long> {
}
