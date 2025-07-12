package de.fhdortmund.schrottverwaltung.immobilie.repo;

import de.fhdortmund.schrottverwaltung.immobilie.ImmoStatusEnum;
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

    @Query("""
            SELECT i
            FROM Immobilie i
            JOIN ImmoStatus s ON i.aktuellerStatusId = s.id
            WHERE s.status = (:statusFilter)
            """)
    List<Immobilie> getAllByStatus(ImmoStatusEnum statusFilter);

    @Query("""
           SELECT i
            FROM Immobilie i
            JOIN ImmoStatus s ON i.aktuellerStatusId = s.id
            WHERE upper(i.bezeichnung) LIKE concat('%', UPPER(:search), '%')
            AND s.status = (:statusFilter)
           """)
    List<Immobilie> getAllByBezeichnungAndStatus(String search, ImmoStatusEnum statusFilter);



    Immobilie getImmobilieById(Long id);

    @Query("""
            SELECT i
            FROM Immobilie i
            WHERE i.archiviert = :archiviert
            """)
    List<Immobilie> findAllByArchiviert(Boolean archiviert);
}
