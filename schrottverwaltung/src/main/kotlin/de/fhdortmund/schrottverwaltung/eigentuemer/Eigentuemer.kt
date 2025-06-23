package de.fhdortmund.schrottverwaltung.eigentuemer

import de.fhdortmund.schrottverwaltung.immoblilie.AdresseT
import jakarta.persistence.*

@Entity
@Table(name = "eigentuemer")
data class Eigentuemer(
    @Column(name = "vorname")
    var vorname: String,
    @Column(name = "nachname")
    var nachname: String,
    @ManyToOne
    @JoinColumn(name = "adresse_id")
    var anschrift: AdresseT,
    var anmerkung: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eigentuemer_id")
    var id: Long? = null
)
