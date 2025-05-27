package de.fhdortmund.schrottverwaltung.mitarbeiter.Repository

import de.fhdortmund.schrottverwaltung.mitarbeiter.Fachbereich
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FachbereichRepository : JpaRepository<Fachbereich, Long> {
}