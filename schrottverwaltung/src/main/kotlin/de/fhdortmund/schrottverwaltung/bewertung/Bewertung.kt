package de.fhdortmund.schrottverwaltung.bewertung

import jakarta.persistence.*

@Entity
data class Bewertung(
    var gewichtung: Int,
    @ManyToOne
    var kriterium: Kriterium
){
    @Id
    @GeneratedValue
    var id: Long? = null
}
