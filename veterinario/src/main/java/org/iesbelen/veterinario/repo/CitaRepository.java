package org.iesbelen.veterinario.repo;

import java.util.List;

import org.iesbelen.veterinario.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface CitaRepository extends JpaRepository<Cita, Long> {

    @Query(value = "SELECT C from Cita C where C.id_mascota = %:id%")
    public List<Cita> getCitasByIdMascota(@Param("id") long id);

    @Query(value = "SELECT C from Cita C where C.id_doctor = %:id%")
    public List<Cita> getCitasByIdDoctor(@Param("id") long id);

}
