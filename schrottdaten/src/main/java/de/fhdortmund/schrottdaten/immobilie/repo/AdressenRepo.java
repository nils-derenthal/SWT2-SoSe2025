package de.fhdortmund.schrottdaten.immobilie.repo;

import de.fhdortmund.schrottdaten.immobilie.entity.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressenRepo extends JpaRepository<Adresse, Long> {
}
