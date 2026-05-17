package com.grupo6.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "Datos de un estudiante registrados en Teach Game")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteDTO {

    @Schema(description = "Identificador unico del estudiante", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nombre completo del estudiante", example = "Laura Martinez", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @Schema(description = "Correo electronico del estudiante", example = "laura.martinez@teachgame.edu", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "Identificador del profesor asignado al estudiante", example = "2")
    private Long profesorId;

    @Schema(description = "Lista de identificadores de materias en las que esta inscrito el estudiante")
    private List<Long> materiaIds;
}
