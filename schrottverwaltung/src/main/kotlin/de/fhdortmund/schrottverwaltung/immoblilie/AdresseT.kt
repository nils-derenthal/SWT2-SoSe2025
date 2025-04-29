package de.fhdortmund.schrottverwaltung.immoblilie

import jakarta.persistence.*

@Entity
data class AdresseT(
    var strasse: String,
    var hausnummer: Int,
    var hausnummerZusatz: String?,
    var plz: Int,
    var ort: String,
    var stadtbezirk: String,
) {
    @Id
    @GeneratedValue
    var id: Long? = null
}

