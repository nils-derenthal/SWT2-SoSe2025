package de.fhdortmund.schrottverwaltung.immoblilie

import jakarta.persistence.*

@Entity
@Table(name = "koordinaten")
data class Koordinaten(
    var xKoordinate: Double,
    var yKoordinate: Double,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}