package de.fhdortmund.schrottverwaltung.eigentuemer.Repository

import de.fhdortmund.schrottverwaltung.eigentuemer.Eigentuemer
import org.springframework.data.repository.CrudRepository

interface EigentuemerRepository : CrudRepository<Eigentuemer, Long> {
}