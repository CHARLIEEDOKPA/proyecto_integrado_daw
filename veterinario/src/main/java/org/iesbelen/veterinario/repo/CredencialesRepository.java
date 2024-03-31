package org.iesbelen.veterinario.repo;

import java.util.Optional;

import org.iesbelen.veterinario.model.Credenciales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CredencialesRepository extends JpaRepository<Credenciales,Long>{
    
    @Query(value = "select C from Credenciales C where C.email = %:email%")
    public Optional<Credenciales> getCredencialesByEmail(@Param("email")String email);


}
