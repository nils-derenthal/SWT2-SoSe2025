package de.fhdortmund.schrottverwaltung.bewertung.Repository

import de.fhdortmund.schrottverwaltung.bewertung.Kriterium
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface KriteriumsRepository : JpaRepository<Kriterium, Long> {
}