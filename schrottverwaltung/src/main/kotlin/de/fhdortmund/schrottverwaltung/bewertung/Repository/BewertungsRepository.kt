package de.fhdortmund.schrottverwaltung.bewertung.Repository

import de.fhdortmund.schrottverwaltung.bewertung.Bewertung
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BewertungsRepository : JpaRepository<Bewertung, Long> {
}