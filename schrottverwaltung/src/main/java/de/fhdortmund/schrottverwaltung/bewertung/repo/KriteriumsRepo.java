package de.fhdortmund.schrottverwaltung.bewertung.repo;

import de.fhdortmund.schrottverwaltung.bewertung.entity.Kriterium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KriteriumsRepo extends JpaRepository<Kriterium, Long> {
}

