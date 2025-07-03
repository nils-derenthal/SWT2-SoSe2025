package de.fhdortmund.schrottdaten.immobilie.repo;

import de.fhdortmund.schrottdaten.immobilie.Immobilie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImmobilienRepo extends JpaRepository<Immobilie, Long> {
}
