package de.fhdortmund.schrottdaten.immobilie;

import de.fhdortmund.schrottdaten.eigentuemer.Eigentuemer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "immobilie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Immobilie {

    @ManyToOne
    @JoinColumn(name = "adresse_id")
    private AdresseT adresse;

    @Column(name = "bezeichnung")
    private String bezeichnung;

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

    @ManyToOne
    @JoinColumn(name = "eigentuemer_id")
    private Eigentuemer eigentuemer;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "immobilie_id")
    private Long id;



}