package com.grupo6.model.entity;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Schema(description = "Estudiante registrado en Teach Game")
@Entity
@Table(name = "estudiantes")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(
        description = "Identificador unico del estudiante",
        example = "1",
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long id;

    @Column
    @Schema(
        description = "Nombre completo del estudiante",
        example = "Laura Martinez",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String nombre;

    @Column
    @Schema(
        description = "Correo electronico del estudiante",
        example = "laura.martinez@teachgame.edu",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String email;

    // Relación con materias
    @ManyToMany(mappedBy = "estudiantes")
    @JsonIgnore
    @Schema(description = "Materias en las que esta inscrito el estudiante")
    private List<Materia> materias;

    // Relación con profesor
    @ManyToOne
    @JoinColumn(name = "profesor_id")
    @JsonBackReference
    @Schema(description = "Profesor asignado al estudiante")
    private Profesor profesor;

}