package de.fhdortmund.schrottverwaltung.immoblilie

import jakarta.persistence.*

@Entity
data class Koordinaten(
    var xKoordinate: Double,
    var yKoordinate: Double,
) {
    @Id
    @GeneratedValue
    var id: Long? = null
}