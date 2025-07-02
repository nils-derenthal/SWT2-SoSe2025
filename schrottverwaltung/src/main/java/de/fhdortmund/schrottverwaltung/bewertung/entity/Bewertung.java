package de.fhdortmund.schrottverwaltung.bewertung.entity;

import de.fhdortmund.schrottverwaltung.immobilie.entity.Immobilie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bewertung")
@Getter
@Setter
public class Bewertung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bewertung_id")
    private Long id;

    @Column(name = "gewichtung")
    private int gewichtung;

    @ManyToOne
    @JoinColumn(name = "kriterium_id")
    private Kriterium kriterium;

    @ManyToOne
    @JoinColumn(name = "immobilie_id") // <- Referenz zur Immobilie
    private Immobilie immobilie;
}

