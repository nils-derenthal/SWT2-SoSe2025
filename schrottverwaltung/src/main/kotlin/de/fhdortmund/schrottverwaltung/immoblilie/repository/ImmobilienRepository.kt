package de.fhdortmund.schrottverwaltung.immoblilie.repository

import de.fhdortmund.schrottverwaltung.immoblilie.Immobilie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ImmobilienRepository : JpaRepository<Immobilie, Long> {

}