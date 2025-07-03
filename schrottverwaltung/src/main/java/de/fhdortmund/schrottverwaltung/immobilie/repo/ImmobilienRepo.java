package de.fhdortmund.schrottverwaltung.immobilie.repo;

import de.fhdortmund.schrottverwaltung.immobilie.entity.Immobilie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImmobilienRepo extends JpaRepository<Immobilie, Long> {

    @Query("""
            Select i
            FROM Immobilie i
            WHERE upper(i.bezeichnung) LIKE concat('%', UPPER(:search), '%')
            """)
    List<Immobilie> getAllByBezeichnung(String search);
}
