package de.fhdortmund.schrottverwaltung.mitarbeiter.repo;

import de.fhdortmund.schrottverwaltung.mitarbeiter.Mitarbeiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MitarbeiterRepo extends JpaRepository<Mitarbeiter, Long> {
    Optional<Mitarbeiter> findByMail(String mail);

    boolean existsByMail(String mail);
}
