package com.grupo6.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Datos publicos de un usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    @Schema(description = "Nombre completo del usuario", example = "Ana Gomez", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @Schema(description = "Correo electronico del usuario", example = "ana.gomez@teachgame.edu", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "Rol asignado al usuario", example = "ESTUDIANTE", requiredMode = Schema.RequiredMode.REQUIRED)
    private String rol;
}
