package de.fhdortmund.schrottverwaltung.bewertung

import jakarta.persistence.*

@Entity
@Table(name = "kriterium")
data class Kriterium(
    @Column(name = "bezeichnung")
    var bezeichnung: String,
    @Column(name = "max_gewichtung")
    var maxGewichtung: Int,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kriterium_id")
    var id: Long? = null
)
