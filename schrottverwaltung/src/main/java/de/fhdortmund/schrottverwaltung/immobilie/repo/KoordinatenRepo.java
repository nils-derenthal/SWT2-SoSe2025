package de.fhdortmund.schrottverwaltung.immobilie.repo;

import de.fhdortmund.schrottverwaltung.immobilie.entity.Koordinaten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KoordinatenRepo extends JpaRepository<Koordinaten, Long> {

    @Query("""
        SELECT k
        FROM Koordinaten k
        WHERE k.herneId = :herneId
        """)
    Koordinaten getByHerneId(Long Dd);

    boolean existsByHerneId(Long herneId);
}
