package de.fhdortmund.schrottverwaltung.bewertung.repo;

import de.fhdortmund.schrottverwaltung.bewertung.entity.Bewertung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BewertungsRepo extends JpaRepository<Bewertung, Long> {
}

