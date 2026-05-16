package com.grupo6.controller;

import com.grupo6.model.entity.Estudiante;
import com.grupo6.service.EstudianteService;
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

@Tag(name = "Estudiantes", description = "Gestion de estudiantes registrados en Teach Game")
@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final EstudianteService service;

    public EstudianteController(EstudianteService service) {
        this.service = service;
    }

    @Operation(summary = "Listar estudiantes", description = "Obtiene todos los estudiantes registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Listado de estudiantes consultado correctamente")
    @GetMapping
    public List<Estudiante> listar() {
        return service.obtenerTodos();
    }

    @Operation(summary = "Crear estudiante", description = "Registra un nuevo estudiante y lo asocia con sus datos academicos.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estudiante creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos del estudiante invalidos", content = @Content)
    })
    @PostMapping
    public Estudiante crear(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del estudiante a registrar",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Estudiante.class))
            )
            @RequestBody Estudiante estudiante) {
        return service.guardar(estudiante);
    }
}
