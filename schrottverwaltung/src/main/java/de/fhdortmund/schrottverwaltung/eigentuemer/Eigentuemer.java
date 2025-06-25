package de.fhdortmund.schrottverwaltung.eigentuemer;

import de.fhdortmund.schrottverwaltung.immobilie.AdresseT;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "eigentuemer")
@Getter
@Setter
public class Eigentuemer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eigentuemer_id")
    private Long id;

    @Column(name = "vorname")
    private String vorname;

    @Column(name = "nachname")
    private String nachname;

    @ManyToOne
    @JoinColumn(name = "adresse_id")
    private AdresseT anschrift;

    private String anmerkung;
}
