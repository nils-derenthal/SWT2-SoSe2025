package de.fhdortmund.schrottverwaltung.mitarbeiter.repo;

import de.fhdortmund.schrottverwaltung.mitarbeiter.Mitarbeiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MitarbeiterRepo extends JpaRepository<Mitarbeiter, Long> {
    Mitarbeiter findByMail(String mail);
}
