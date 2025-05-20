package de.fhdortmund.schrottverwaltung.bewertung.Repository

import de.fhdortmund.schrottverwaltung.bewertung.Bewertung
import org.springframework.data.repository.CrudRepository

interface BewertungsRepository : CrudRepository<Bewertung, Long> {
}