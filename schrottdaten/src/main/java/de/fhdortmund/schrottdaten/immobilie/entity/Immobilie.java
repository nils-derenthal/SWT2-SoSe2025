package de.fhdortmund.schrottdaten.immobilie.entity;

import de.fhdortmund.schrottdaten.eigentuemer.entity.Eigentuemer;
import de.fhdortmund.schrottdaten.immobilie.EigentumsForm;
import de.fhdortmund.schrottdaten.immobilie.Gebaeudetyp;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;


@Entity
@Table(name = "immobilie")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Immobilie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "immobilie_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "adresse_id")
    private Adresse adresse;

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
    @Column(name = "gebaeude_typ")
    private Gebaeudetyp gebaeudeTyp;

    @Enumerated(EnumType.STRING)
    @Column(name = "eigentums_form")
    private EigentumsForm eigentumsForm;

    @ManyToOne
    @JoinColumn(name = "eigentuemer_id")
    private Eigentuemer eigentuemer;

    @Column(name = "bild", columnDefinition = "TEXT")
    private byte[] bild;
}
