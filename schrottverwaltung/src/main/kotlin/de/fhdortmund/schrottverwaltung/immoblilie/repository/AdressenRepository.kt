package de.fhdortmund.schrottverwaltung.immoblilie.repository

import de.fhdortmund.schrottverwaltung.immoblilie.AdresseT
import org.springframework.data.repository.CrudRepository

interface AdressenRepository : CrudRepository<AdresseT, Long> {
}