package com.grupo6.model.entity;

import java.util.List;

// import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Estudiante registrado en Teach Game")
@Entity
@Table(name = "estudiantes")    
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Estudiante {

    // @JsonBackReference
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador unico del estudiante", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    // @JsonBackReference
    @ManyToMany(mappedBy = "estudiantes")
    @Schema(description = "Materias en las que esta inscrito el estudiante")
    private List<Materia> materias;

    // @SuppressWarnings("unused")
    @Column
    @Schema(description = "Nombre completo del estudiante", example = "Laura Martinez", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;
    // @SuppressWarnings("unused")
    @Column
    @Schema(description = "Correo electronico del estudiante", example = "laura.martinez@teachgame.edu", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @ManyToOne
    @JoinColumn(name = "profesor_id")
    @Schema(description = "Profesor asignado al estudiante")
    private Profesor profesor;

}
