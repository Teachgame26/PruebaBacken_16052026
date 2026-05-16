package com.grupo6.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// import com.fasterxml.jackson.annotation.JsonBackReference;
// import com.fasterxml.jackson.annotation.JsonManagedReference;

@Schema(description = "Profesor registrado en Teach Game")
@Entity
@Table(name = "profesores")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Profesor {

    // @JsonBackReference
    // @JsonManagedReference
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

    // public List<Estudiante> getEstudiantes() {
    //     // TODO Auto-generated method stub
        
    //     throw new UnsupportedOperationException("Unimplemented method 'getEstudiantes'");
    // }

    // public void setNombre(String string) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'setNombre'");
    // }

    // public void setEmail(String string) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'setEmail'");
    // }

    // public void setMaterias(List<Materia> of) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'setMaterias'");
    // }

    // // Getters y Setters
}
