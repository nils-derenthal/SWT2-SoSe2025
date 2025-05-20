package de.fhdortmund.schrottverwaltung.bewertung.Repository

import de.fhdortmund.schrottverwaltung.bewertung.Kriterium
import org.springframework.data.repository.CrudRepository

interface KriteriumsRepository : CrudRepository<Kriterium, Long> {
}