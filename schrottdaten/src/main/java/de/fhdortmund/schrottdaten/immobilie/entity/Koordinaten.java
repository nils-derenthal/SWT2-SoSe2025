package de.fhdortmund.schrottdaten.immobilie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "koordinaten")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Koordinaten {

    @Column(name = "x")
    private Double xKoordinate;

    @Column(name = "y")
    private Double yKoordinate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "koordinaten_id")
    private Long id;
}
