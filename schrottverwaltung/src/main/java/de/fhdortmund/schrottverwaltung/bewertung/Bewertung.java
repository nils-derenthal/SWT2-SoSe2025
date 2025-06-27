package de.fhdortmund.schrottverwaltung.bewertung;

import de.fhdortmund.schrottverwaltung.immobilie.Immobilie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bewertung")
@Getter
@Setter
public class Bewertung {

    @Column(name = "gewichtung")
    private int gewichtung;

    @ManyToOne
    @JoinColumn(name = "kriterium_id")
    private Kriterium kriterium;

    @ManyToOne
    @JoinColumn(name = "immobilie_id") // <- Referenz zur Immobilie
    private Immobilie immobilie;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bewertung_id")
    private Long id;
}

