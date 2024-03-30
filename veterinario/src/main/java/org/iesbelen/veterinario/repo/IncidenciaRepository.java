package org.iesbelen.veterinario.repo;

import org.iesbelen.veterinario.model.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia,Long>{
    
}
