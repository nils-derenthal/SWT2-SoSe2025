package de.fhdortmund.schrottverwaltung.eigentuemer

import de.fhdortmund.schrottverwaltung.immoblilie.AdresseT
import jakarta.persistence.*

@Entity
data class Eigentuemer(
    var vorname: String,
    var nachname: String,
    @ManyToOne
    var anschrift: AdresseT,
    var anmerkung: String
){
    @Id
    @GeneratedValue
    var id: Long? = null
}
