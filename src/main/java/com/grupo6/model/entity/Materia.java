package com.grupo6.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// import com.fasterxml.jackson.annotation.JsonManagedReference;

@Schema(description = "Materia academica disponible en Teach Game")
@Entity
@Table(name = "materias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Materia {

    // @JsonManagedReference
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador unico de la materia", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    // @SuppressWarnings("unused")
    @Schema(description = "Nombre de la materia", example = "Matematicas", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;

    @ManyToMany
    @JoinTable(
        name = "materia_profesor",
        joinColumns = @JoinColumn(name = "materia_id"),
        inverseJoinColumns = @JoinColumn(name = "profesor_id")
    )
    @Schema(description = "Profesores que dictan la materia")
    private List<Profesor> profesores;

    @ManyToMany
    @JoinTable(
        name = "materia_estudiante",
        joinColumns = @JoinColumn(name = "materia_id"),
        inverseJoinColumns = @JoinColumn(name = "estudiante_id")
    )
    @Schema(description = "Estudiantes inscritos en la materia")
    private List<Estudiante> estudiantes;

    // public void setNombre(String nombre2) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'setNombre'");
    // }

    // Getters y Setters
}
