package de.fhdortmund.schrottverwaltung.bewertung

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity
data class Kriterium(
    var bezeichnung: String,
    var maxGewichtung: Int
)
{
    @Id
    @GeneratedValue
    var id: Long? = null
}
