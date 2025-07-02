package de.fhdortmund.schrottverwaltung.immobilie.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "koordinaten")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Koordinaten {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double xKoordinate;

    private Double yKoordinate;
}
