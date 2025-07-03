package de.fhdortmund.schrottverwaltung.mitarbeiter.repo;

import de.fhdortmund.schrottverwaltung.mitarbeiter.Fachbereich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FachbereichRepo extends JpaRepository<Fachbereich, Long> {
}
