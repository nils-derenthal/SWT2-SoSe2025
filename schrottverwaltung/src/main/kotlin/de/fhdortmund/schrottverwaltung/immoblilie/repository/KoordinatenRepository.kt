package de.fhdortmund.schrottverwaltung.immoblilie.repository

import de.fhdortmund.schrottverwaltung.immoblilie.Koordinaten
import org.springframework.data.repository.CrudRepository

interface KoordinatenRepository : CrudRepository<Koordinaten, Long> {
}