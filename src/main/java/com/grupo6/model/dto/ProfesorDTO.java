package com.grupo6.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "Datos de un profesor registrados en Teach Game")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorDTO {

    @Schema(description = "Identificador unico del profesor", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nombre completo del profesor", example = "Carlos Ramirez", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @Schema(description = "Correo electronico del profesor", example = "carlos.ramirez@teachgame.edu", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "Lista de identificadores de materias dictadas por el profesor")
    private List<Long> materiaIds;

    @Schema(description = "Lista de identificadores de estudiantes asignados al profesor")
    private List<Long> estudianteIds;
}
