package org.iesbelen.veterinario.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecomendacionRequest {

    @NotNull
    @NotBlank
    private String texto;
    @NotNull
    @NotBlank
    private String sobre;
    @NotNull
    private long id_mascota;

}
