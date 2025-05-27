package de.fhdortmund.schrottverwaltung.immoblilie

import jakarta.persistence.*

@Entity
@Table(name = "immobilien_status")
data class ImmoStatus(
    @Enumerated(EnumType.STRING)
    var status: ImmoStatusEnum,
    @Column(name = "beschreibung")
    var beschreibung: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "immobilien_status_id")
    var id: Long? = null
)
