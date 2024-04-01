package org.iesbelen.veterinario.model;

import java.sql.Date;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class IncidenciaRequest {


    @NotNull
    private long id_mascota;

    @NotBlank
    @NotNull
    private String observaciones;

    @NotNull
    @NotBlank
    private String rol;
}
