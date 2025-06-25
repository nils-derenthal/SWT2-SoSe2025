package de.fhdortmund.schrottverwaltung.immobilie;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "immobilien_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImmoStatus {

    @Enumerated(EnumType.STRING)
    private ImmoStatusEnum status;

    @Column(name = "beschreibung")
    private String beschreibung;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "immobilien_status_id")
    private Long id;
}
