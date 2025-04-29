package de.fhdortmund.schrottverwaltung.immoblilie

import de.fhdortmund.schrottverwaltung.bewertung.Bewertung
import de.fhdortmund.schrottverwaltung.eigentuemer.Eigentuemer
import jakarta.persistence.*

@Entity
data class Immobilie(
    @ManyToOne
    var adresse: AdresseT,
    var bezeichnung: String,
    var archiviert: Boolean,
    var zustand: String,
    @OneToOne
    var koordinaten: Koordinaten,
    var Gemarkung: String,
    var Flur: String,
    var Flurstueck: String,
    var quadratMeter: Int,
    var gebaeudeTyp: Gebaeudetyp,
    var eigentumsForm: EigentumsForm,
    @OneToMany
    var bewertungen: List<Bewertung>,
    @ManyToOne
    var eigentuemer: Eigentuemer?,
    @ManyToOne
    var status: ImmoStatus
) {
    @Id
    @GeneratedValue
    val id: Long? = null
}
