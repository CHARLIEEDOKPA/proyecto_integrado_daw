package org.iesbelen.veterinario.model;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
    name = "incidencia",
    schema = "veterinario_proyecto_integrado"
)
public class Incidencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @CreationTimestamp
    @Column(nullable = false)
    private Date fecha;
    @JsonIgnore
    @Column(nullable = false)
    private long id_mascota;
    @JsonIgnore
    @Column(nullable = false)
    private long id_doctor;
    @Column(nullable = false,length = 1500)
    private String observaciones;



}
