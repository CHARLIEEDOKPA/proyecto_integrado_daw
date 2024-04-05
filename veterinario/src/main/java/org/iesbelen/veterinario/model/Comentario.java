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
    name = "comentario",
    schema = "veterinario_proyecto_integrado"
)
public class Comentario {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 500)
    private String texto;

}
