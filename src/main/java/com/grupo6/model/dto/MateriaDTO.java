package com.grupo6.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "Datos de una materia academica en Teach Game")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MateriaDTO {

    @Schema(description = "Identificador unico de la materia", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nombre de la materia", example = "Matematicas", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @Schema(description = "Lista de identificadores de profesores que dictan la materia")
    private List<Long> profesorIds;

    @Schema(description = "Lista de identificadores de estudiantes inscritos en la materia")
    private List<Long> estudianteIds;
}
