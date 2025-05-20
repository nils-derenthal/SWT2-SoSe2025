package de.fhdortmund.schrottverwaltung.immoblilie.repository

import de.fhdortmund.schrottverwaltung.immoblilie.Immobilie
import org.springframework.data.repository.CrudRepository

interface ImmobilienRepository : CrudRepository<Immobilie, Long> {

}