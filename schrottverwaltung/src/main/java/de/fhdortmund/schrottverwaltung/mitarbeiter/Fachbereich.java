package de.fhdortmund.schrottverwaltung.mitarbeiter;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fachbereich")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fachbereich {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fachbereichNr;

    private String bezeichnung;
}
