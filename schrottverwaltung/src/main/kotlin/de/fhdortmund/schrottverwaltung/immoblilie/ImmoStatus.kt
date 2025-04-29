package de.fhdortmund.schrottverwaltung.immoblilie

import jakarta.persistence.*

@Entity
data class ImmoStatus(
    var status: ImmoStatusEnum,
    var beschreibung: String
){
    @Id
    @GeneratedValue
    var id: Long? = null
}
