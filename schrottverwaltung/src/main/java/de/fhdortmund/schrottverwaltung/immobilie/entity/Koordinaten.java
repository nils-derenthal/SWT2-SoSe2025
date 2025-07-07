package de.fhdortmund.schrottverwaltung.immobilie.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "koordinaten")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Koordinaten {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "koordinaten_id")
    private Long id;

    @Column(name = "x_koordinate")
    private Double xKoordinate;

    @Column(name = "y_koordinate")
    private Double yKoordinate;
}
