package org.iesbelen.veterinario.repo;

import org.iesbelen.veterinario.model.Duenyo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DuenyoRepository extends JpaRepository<Duenyo,Long>{
    
}
