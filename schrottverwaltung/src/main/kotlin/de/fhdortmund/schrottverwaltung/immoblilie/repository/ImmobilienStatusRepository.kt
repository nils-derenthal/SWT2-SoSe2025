package de.fhdortmund.schrottverwaltung.immoblilie.repository

import de.fhdortmund.schrottverwaltung.immoblilie.ImmoStatus
import org.springframework.data.repository.CrudRepository

interface ImmobilienStatusRepository : CrudRepository<ImmoStatus, Long> {
}