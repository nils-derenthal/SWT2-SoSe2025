package de.fhdortmund.schrottverwaltung.mitarbeiter

import jakarta.persistence.*

@Entity
@Table(name = "mitarbeiter")
data class Mitarbeiter(
    var vorname: String,
    var nachname: String,
    var mail: String,
    var fachbereichNr: Long,
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}
