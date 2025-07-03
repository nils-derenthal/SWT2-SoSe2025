package de.fhdortmund.schrottdaten.immobilie;

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

    private Double xKoordinate;

    private Double yKoordinate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
