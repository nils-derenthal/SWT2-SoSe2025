package de.fhdortmund.schrottverwaltung.immoblilie

import de.fhdortmund.schrottverwaltung.bewertung.Bewertung
import de.fhdortmund.schrottverwaltung.eigentuemer.Eigentuemer
import jakarta.persistence.*
@Entity
@Table(name = "immobilie")
data class Immobilie(
    @ManyToOne
    @JoinColumn(name = "adresse_id")
    var adresse: AdresseT,
    @Column(name = "bezeichnung")
    var bezeichnung: String,
    @Column(name = "archiviert")
    var archiviert: Boolean,
    @Column(name = "zustand")
    var zustand: String,
    @OneToOne
    @JoinColumn(name = "koordinaten_id")
    var koordinaten: Koordinaten,
    @Column(name = "gemarkung")
    var gemarkung: String,
    @Column(name = "flur")
    var flur: String,
    @Column(name = "flurstueck")
    var flurstueck: String,
    @Column(name = "quadratmeter")
    var quadratMeter: Int,
    @Enumerated(EnumType.STRING)
    var gebaeudeTyp: Gebaeudetyp,
    @Enumerated(EnumType.STRING)
    var eigentumsForm: EigentumsForm,
    @OneToMany(mappedBy = "immobilie", cascade = [CascadeType.ALL], orphanRemoval = true)
    var bewertungen: List<Bewertung>,
    @ManyToOne
    @JoinColumn(name = "eigentuemer_id")
    var eigentuemer: Eigentuemer?,
    @ManyToOne
    @JoinColumn(name = "status_id")
    var status: ImmoStatus,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "immobilie_id")
    val id: Long? = null
)

