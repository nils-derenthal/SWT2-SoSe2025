package de.fhdortmund.schrottverwaltung.bewertung

import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

data class Kriterium(
    var bezeichnung: String,
    var maxGewichtung: Int
)
{
    @Id
    @GeneratedValue
    var id: Long? = null
}
