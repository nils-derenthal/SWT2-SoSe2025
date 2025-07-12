package de.fhdortmund.schrottdaten.immobilie.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adresse")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adresse {

    @Column(name = "strasse")
    private String strasse;

    @Column(name = "hausnummer")
    private int hausnummer;

    @Column(name = "hausnummer_zusatz")
    private String hausnummerZusatz;

    @Column(name = "plz")
    private int plz;

    @Column(name = "ort")
    private String ort;

    @Column(name = "stadtbezirk")
    private String stadtbezirk;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adresse_id")
    private Long id;
}
