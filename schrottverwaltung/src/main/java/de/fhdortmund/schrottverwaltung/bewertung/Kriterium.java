package de.fhdortmund.schrottverwaltung.bewertung;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "kriterium")
@Getter
@Setter
public class Kriterium {
    @Column(name = "bezeichnung")
    private String bezeichnung;

    @Column(name = "max_gewichtung")
    private int maxGewichtung;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kriterium_id")
    private Long id;

    public Kriterium(String bezeichnung, int maxGewichtung) {
        this.bezeichnung = bezeichnung;
        this.maxGewichtung = maxGewichtung;
    }
}

