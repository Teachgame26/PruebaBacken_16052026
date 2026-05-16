package com.grupo6.controller;

import com.grupo6.model.entity.Usuario;
import com.grupo6.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Usuarios", description = "Consulta de usuarios del sistema Teach Game")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @Operation(summary = "Listar usuarios", description = "Obtiene todos los usuarios registrados con sus roles.")
    @ApiResponse(responseCode = "200", description = "Listado de usuarios consultado correctamente")
    @GetMapping
    public List<Usuario> listar() {
        return service.obtenerTodos();
    }
}
