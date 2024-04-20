package org.iesbelen.veterinario.repo;

import java.util.List;

import org.iesbelen.veterinario.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion,Long>{
    
    @Query("SELECT P from Publicacion P where P.id_duenyo = %:id_duenyo%")
    public List<Publicacion> getPublicacionesByIdDuenyo(@Param("id_duenyo") Long id);

}
