package de.fhdortmund.schrottverwaltung.mitarbeiter

import jakarta.persistence.*

@Entity
@Table(name = "fachbereich")
data class Fachbereich(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var fachbereichNr: Long,
    var bezeichnung: String,
)
