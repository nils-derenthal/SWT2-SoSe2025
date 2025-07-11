package de.fhdortmund.schrottverwaltung.immobilie.repo;

import de.fhdortmund.schrottverwaltung.immobilie.entity.ImmoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImmobilienStatusRepo extends JpaRepository<ImmoStatus, Long> {

    ImmoStatus findImmobilienStatusById(Long id);
}