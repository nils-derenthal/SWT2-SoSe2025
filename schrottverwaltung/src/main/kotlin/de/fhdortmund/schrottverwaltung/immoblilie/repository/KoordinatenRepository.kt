package de.fhdortmund.schrottverwaltung.immoblilie.repository

import de.fhdortmund.schrottverwaltung.immoblilie.Koordinaten
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface KoordinatenRepository : JpaRepository<Koordinaten, Long> {
}