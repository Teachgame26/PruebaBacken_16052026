package com.grupo6.controller;

import com.grupo6.model.entity.Materia;
import com.grupo6.service.MateriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Materias", description = "Gestion de materias, profesores y estudiantes asociados")
@RestController
@RequestMapping("/materias")
public class MateriaController {

    private final MateriaService service;

    public MateriaController(MateriaService service) {
        this.service = service;
    }

    @Operation(summary = "Listar materias", description = "Obtiene todas las materias disponibles en Teach Game.")
    @ApiResponse(responseCode = "200", description = "Listado de materias consultado correctamente")
    @GetMapping
    public List<Materia> listar() {
        return service.obtenerTodas();
    }

    @Operation(summary = "Crear materia", description = "Registra una nueva materia con sus relaciones academicas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Materia creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de la materia invalidos", content = @Content)
    })
    @PostMapping
    public Materia crear(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la materia a registrar",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Materia.class))
            )
            @RequestBody Materia materia) {
        return service.guardar(materia);
    }
}
