package de.fhdortmund.schrottverwaltung.immobilie;

import de.fhdortmund.schrottverwaltung.bewertung.Bewertung;
import de.fhdortmund.schrottverwaltung.eigentuemer.Eigentuemer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "immobilie")
@Getter
@Setter
public class Immobilie {

    @ManyToOne
    @JoinColumn(name = "adresse_id")
    private AdresseT adresse;

    @Column(name = "bezeichnung")
    private String bezeichnung;

    @Column(name = "archiviert")
    private Boolean archiviert;

    @Column(name = "zustand")
    private String zustand;

    @OneToOne
    @JoinColumn(name = "koordinaten_id")
    private Koordinaten koordinaten;

    @Column(name = "gemarkung")
    private String gemarkung;

    @Column(name = "flur")
    private String flur;

    @Column(name = "flurstueck")
    private String flurstueck;

    @Column(name = "quadratmeter")
    private Integer quadratMeter;

    @Enumerated(EnumType.STRING)
    private Gebaeudetyp gebaeudeTyp;

    @Enumerated(EnumType.STRING)
    private EigentumsForm eigentumsForm;

    @OneToMany(mappedBy = "immobilie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bewertung> bewertungen;

    @ManyToOne
    @JoinColumn(name = "eigentuemer_id")
    private Eigentuemer eigentuemer;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private ImmoStatus status;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "immobilie_id")
    private Long id;

    @Column(name="bild")
    private String bildBase64;

}