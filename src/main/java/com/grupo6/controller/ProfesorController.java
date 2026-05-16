package com.grupo6.controller;

import com.grupo6.model.entity.Estudiante;
import com.grupo6.model.entity.Profesor;
import com.grupo6.service.ProfesorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Profesores", description = "Gestion de profesores y sus estudiantes asignados")
@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    private final ProfesorService service;

    public ProfesorController(ProfesorService service) {
        this.service = service;
    }

    @Operation(summary = "Listar profesores", description = "Obtiene todos los profesores registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Listado de profesores consultado correctamente")
    @GetMapping
    public List<Profesor> listar() {
        return service.obtenerTodos();
    }

    @Operation(summary = "Obtener profesor por ID", description = "Busca un profesor por su identificador unico.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profesor encontrado"),
            @ApiResponse(responseCode = "404", description = "Profesor no encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public Profesor obtener(
            @Parameter(description = "ID numerico del profesor", example = "1", required = true)
            @PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @Operation(summary = "Crear profesor", description = "Registra un nuevo profesor en Teach Game.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profesor creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos del profesor invalidos", content = @Content)
    })
    @PostMapping
    public Profesor crear(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del profesor a registrar",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Profesor.class))
            )
            @RequestBody Profesor profesor) {
        return service.guardar(profesor);
    }

    @Operation(summary = "Listar estudiantes de un profesor", description = "Obtiene los estudiantes asignados a un profesor especifico.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estudiantes del profesor consultados correctamente"),
            @ApiResponse(responseCode = "404", description = "Profesor no encontrado", content = @Content)
    })
    @GetMapping("/{id}/estudiantes")
    public List<Estudiante> obtenerEstudiantes(
            @Parameter(description = "ID numerico del profesor", example = "1", required = true)
            @PathVariable Long id) {
        return service.obtenerEstudiantesPorProfesor(id);
    }
}
