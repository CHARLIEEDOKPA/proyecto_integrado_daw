package org.iesbelen.veterinario.repo;

import org.iesbelen.veterinario.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota,Long>{
    
}
