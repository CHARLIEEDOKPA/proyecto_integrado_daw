package org.iesbelen.veterinario.repo;

import org.iesbelen.veterinario.model.Credenciales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredencialesRepository extends JpaRepository<Credenciales,Long>{
    
}
