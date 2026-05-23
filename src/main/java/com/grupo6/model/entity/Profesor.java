package com.grupo6.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

@Schema(description = "Profesor registrado en Teach Game")
@Entity
@Table(name = "profesores")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Profesor {

    // @JsonBackReference- se agrega para dejar el codigo mas limpio


    @OneToMany(mappedBy = "profesor")
    @JsonManagedReference
    private List<Materia> materias;



    // @JsonManagedReference 

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador unico del profesor", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    
    // @SuppressWarnings("unused")
    @Column
    @Schema(description = "Nombre completo del profesor", example = "Carlos Ramirez", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;
    // @SuppressWarnings("unused")
    @Column
    @Schema(description = "Correo electronico del profesor", example = "carlos.ramirez@teachgame.edu", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @ManyToMany(mappedBy = "profesores")
    @Schema(description = "Materias dictadas por el profesor")
    private List<Materia> materias;

    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL)
    @Schema(description = "Estudiantes asignados al profesor")
    private List<Estudiante> estudiantes;

    