package de.fhdortmund.schrottverwaltung.mitarbeiter.Repository

import de.fhdortmund.schrottverwaltung.mitarbeiter.Mitarbeiter
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MitarbeiterRepository : JpaRepository<Mitarbeiter, Long> {
}