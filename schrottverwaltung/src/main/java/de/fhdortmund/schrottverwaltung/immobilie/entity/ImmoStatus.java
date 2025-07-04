package de.fhdortmund.schrottverwaltung.immobilie.entity;

import de.fhdortmund.schrottverwaltung.immobilie.ImmoStatusEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "immobilien_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImmoStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "immobilien_status_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ImmoStatusEnum status;

    @Column(name = "beschreibung")
    private String beschreibung;

    @ManyToOne
    @JoinColumn(name = "immobilie_id") // <- Referenz zur Immobilie
    private Immobilie immobilie;
}
