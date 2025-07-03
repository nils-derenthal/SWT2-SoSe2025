package de.fhdortmund.schrottdaten.immobilie.repo;

import de.fhdortmund.schrottdaten.immobilie.AdresseT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressenRepo extends JpaRepository<AdresseT, Long> {
}
