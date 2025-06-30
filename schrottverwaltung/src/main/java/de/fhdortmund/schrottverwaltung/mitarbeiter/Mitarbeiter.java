package de.fhdortmund.schrottverwaltung.mitarbeiter;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mitarbeiter")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mitarbeiter {

    private String vorname;

    private String nachname;

    private String password;

    private String mail;

    private Long fachbereichNr;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
