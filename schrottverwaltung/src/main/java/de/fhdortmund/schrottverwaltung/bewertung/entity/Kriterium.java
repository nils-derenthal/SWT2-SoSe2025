package de.fhdortmund.schrottverwaltung.bewertung.entity;

import de.fhdortmund.schrottverwaltung.mitarbeiter.Fachbereich;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "kriterium")
@Getter
@Setter
@NoArgsConstructor
public class Kriterium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kriterium_id")
    private Long id;

    @Column(name = "bezeichnung")
    private String bezeichnung;

    @Column(name = "max_gewichtung")
    private int maxGewichtung;

    @ManyToOne
    @JoinColumn(name = "fachbereich_id")
    private Fachbereich fachbereich;

    public Kriterium(String bezeichnung, int maxGewichtung) {
        this.bezeichnung = bezeichnung;
        this.maxGewichtung = maxGewichtung;
    }
}

