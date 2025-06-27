package de.fhdortmund.schrottverwaltung.immobilie;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "koordinaten")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Koordinaten {

    private Double xKoordinate;

    private Double yKoordinate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
