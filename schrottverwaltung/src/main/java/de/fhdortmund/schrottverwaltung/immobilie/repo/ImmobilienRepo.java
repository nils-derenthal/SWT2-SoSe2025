package de.fhdortmund.schrottverwaltung.immobilie.repo;

import de.fhdortmund.schrottverwaltung.immobilie.Immobilie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImmobilienRepo extends JpaRepository<Immobilie, Long> {

    @Query("""
                SELECT  i
                FROM Immobilie i
                WHERE i.archiviert=true
        """)
    List<Immobilie> findAllArchivedImmobilien();
    //TODO ändern wenn DTO steht SELECT new de.fhdortmund.schrottverwaltung.immobilie.DTO.ImmobilienDTO(Hier alles rein was ins DTO muss)

}
