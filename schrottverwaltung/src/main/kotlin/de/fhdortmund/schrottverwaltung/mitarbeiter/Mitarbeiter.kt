package de.fhdortmund.schrottverwaltung.mitarbeiter

import jakarta.persistence.*

@Entity
data class Mitarbeiter(
    var vorname: String,
    var nachname: String,
    var mail: String,
    var fachbereichNr: Long,
){
    @Id
    @GeneratedValue
    var id: Long? = null
}
