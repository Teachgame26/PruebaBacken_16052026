package com.grupo6.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

@Schema(description = "Usuario del sistema con rol academico")
@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
        description = "Identificador unico del usuario",
        example = "1",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Column
    @Schema(
        description = "Nombre completo del usuario",
        example = "Ana Gomez",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String nombre;

    @Column
    @Schema(
        description = "Correo electronico del usuario",
        example = "ana.gomez@teachgame.edu",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String email;

    @Schema(
        description = "Contrasena del usuario",
        example = "TeachGame123",
        requiredMode = Schema.RequiredMode.REQUIRED,
        accessMode = Schema.AccessMode.WRITE_ONLY
    )
    private String password;

    @Enumerated(EnumType.STRING)
    @Schema(
        description = "Rol del usuario dentro del sistema",
        example = "ESTUDIANTE",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Rol rol;

    // Relación con profesores
    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    private List<Profesor> profesores;

    public enum Rol {
        ESTUDIANTE,
        PROFESOR
    }

}