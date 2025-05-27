package de.fhdortmund.schrottverwaltung.bewertung

import de.fhdortmund.schrottverwaltung.immoblilie.Immobilie
import jakarta.persistence.*

@Entity
@Table(name = "bewertung")
data class Bewertung(

    @Column(name = "gewichtung")
    var gewichtung: Int,
    @ManyToOne
    @JoinColumn(name = "kriterium_id")
    var kriterium: Kriterium,
    @ManyToOne
    @JoinColumn(name = "immobilie_id") // <- Referenz zur Immobilie
    var immobilie: Immobilie,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bewertung_id")
    var id: Long? = null
)
