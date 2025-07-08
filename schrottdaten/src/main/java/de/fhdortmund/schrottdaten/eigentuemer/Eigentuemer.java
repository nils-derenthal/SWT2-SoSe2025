package de.fhdortmund.schrottdaten.eigentuemer;

import de.fhdortmund.schrottdaten.immobilie.Adresse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "eigentuemer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Eigentuemer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eigentuemer_id")
    private Long id;

    @Column(name = "vorname")
    private String vorname;

    @Column(name = "nachname")
    private String nachname;

    @ManyToOne
    @JoinColumn(name = "adresse_id")
    private Adresse anschrift;

}
