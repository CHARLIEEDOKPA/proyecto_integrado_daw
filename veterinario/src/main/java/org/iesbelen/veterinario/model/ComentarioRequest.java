package org.iesbelen.veterinario.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioRequest {
    
    @NotBlank
    private String texto;

    @NotNull
    private long id_publicacion;



}
