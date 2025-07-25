package de.fhdortmund.schrottverwaltung.immobilie.entity;

import de.fhdortmund.schrottverwaltung.bewertung.entity.Bewertung;
import de.fhdortmund.schrottverwaltung.eigentuemer.entity.Eigentuemer;
import de.fhdortmund.schrottverwaltung.immobilie.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "immobilie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Immobilie {

    @Id
    @Column(name = "immobilie_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

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
    @Column(name = "gebaeudetyp")
    private Gebaeudetyp gebaeudetyp;

    @Enumerated(EnumType.STRING)
    @Column(name = "eigentumsform")
    private EigentumsForm eigentumsForm;

    @OneToMany(mappedBy = "immobilie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bewertung> bewertungen;

    @ManyToOne
    @JoinColumn(name = "eigentuemer_id")
    private Eigentuemer eigentuemer;

    @OneToMany(mappedBy = "immobilie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImmoStatus> immoStati;

    private Integer aktuellerStatusId;

    @Column(name="bild")
    private byte[] bild;
}
