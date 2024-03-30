package org.iesbelen.veterinario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
    name = "recomendacion",
    schema = "veterinario_proyecto_integrado"
)
public class Recomendacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 125)
    private String sobre;
    @Column(nullable = false, length = 2000)
    private String texto;
    @Column(nullable = false)
    private long id_mascota;
    @Column(nullable = false)
    private long id_doctor;


}
