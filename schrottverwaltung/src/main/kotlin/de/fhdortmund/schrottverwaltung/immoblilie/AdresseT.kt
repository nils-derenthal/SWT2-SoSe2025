package de.fhdortmund.schrottverwaltung.immoblilie

import jakarta.persistence.*

@Entity
@Table(name = "adresse")
data class AdresseT(
    @Column(name = "strasse")
    var strasse: String,
    @Column(name = "hausnummer")
    var hausnummer: Int,
    @Column(name = "hausnummer_zusatz")
    var hausnummerZusatz: String?,
    @Column(name = "plz")
    var plz: Int,
    @Column(name = "ort")
    var ort: String,
    @Column(name = "stadtbezirk")
    var stadtbezirk: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adresse_id")
    var id: Long? = null
)

