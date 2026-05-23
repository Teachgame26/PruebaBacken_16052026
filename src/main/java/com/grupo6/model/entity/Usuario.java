package com.grupo6.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Usuario del sistema con rol academico")
@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador unico del usuario", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    // @SuppressWarnings("unused")
    @Column
    @Schema(description = "Nombre completo del usuario", example = "Ana Gomez", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nombre;
    // @SuppressWarnings("unused")
    @Column
    @Schema(description = "Correo electronico del usuario", example = "ana.gomez@teachgame.edu", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    // @SuppressWarnings("unused")
    @Schema(description = "Contrasena del usuario", example = "TeachGame123", requiredMode = Schema.RequiredMode.REQUIRED, accessMode = Schema.AccessMode.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Rol del usuario dentro del sistema", example = "ESTUDIANTE", requiredMode = Schema.RequiredMode.REQUIRED)
    private Rol rol;

    public enum Rol {
        ESTUDIANTE,
        PROFESOR
    }

    // public void setNombre(String nombre2) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'setNombre'");
    // }

    // public void setEmail(String email2) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'setEmail'");
    // }

    // public void setPassword(String string) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'setPassword'");
    // }

    // public void setRol(Rol rol2) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'setRol'");
    // }

    // Getters y Setters
}
