package com.grupo6.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos publicos de un usuario")
public class UsuarioDTO {

    @SuppressWarnings("unused")
    @Schema(description = "Nombre completo del usuario", example = "Ana Gomez", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;
    @SuppressWarnings("unused")
    @Schema(description = "Correo electronico del usuario", example = "ana.gomez@teachgame.edu", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    @SuppressWarnings("unused")
    @Schema(description = "Rol asignado al usuario", example = "ESTUDIANTE", requiredMode = Schema.RequiredMode.REQUIRED)
    private String rol;

    // Getters y Setters
}
