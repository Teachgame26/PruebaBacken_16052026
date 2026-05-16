package com.grupo6.config;

import com.grupo6.model.entity.*;
import com.grupo6.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;

import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner init(
            ProfesorRepository profesorRepo,
            EstudianteRepository estudianteRepo,
            MateriaRepository materiaRepo
    ) {
        return args -> {

            // ========================
            // 👨‍🏫 PROFESORES
            // ========================
            Profesor p1 = new Profesor();
            p1.setNombre("Carlos Pérez");
            p1.setEmail("carlos@uni.com");

            Profesor p2 = new Profesor();
            p2.setNombre("Ana Gómez");
            p2.setEmail("ana@uni.com");

            profesorRepo.saveAll(List.of(p1, p2));

            // ========================
            // 👨‍🎓 ESTUDIANTES
            // ========================
            Estudiante e1 = crearEst("Juan", "juan@uni.com", p1);
            Estudiante e2 = crearEst("Maria", "maria@uni.com", p1);
            Estudiante e3 = crearEst("Luis", "luis@uni.com", p1);
            Estudiante e4 = crearEst("Sofia", "sofia@uni.com", p2);
            Estudiante e5 = crearEst("Pedro", "pedro@uni.com", p2);

            estudianteRepo.saveAll(List.of(e1, e2, e3, e4, e5));

            // ========================
            // 📚 MATERIAS
            // ========================
            Materia m1 = crearMateria("Matemáticas");
            Materia m2 = crearMateria("Historia");
            Materia m3 = crearMateria("Biología");
            Materia m4 = crearMateria("Física");
            Materia m5 = crearMateria("Literatura");
            Materia m6 = crearMateria("Geografía");

            materiaRepo.saveAll(List.of(m1, m2, m3, m4, m5, m6));

            // ========================
            // 🔗 RELACIONES (OPCIONAL)
            // ========================
            // Ejemplo: asignar materias a profesores

            p1.setMaterias(List.of(m1, m2, m4)); // Carlos
            p2.setMaterias(List.of(m3, m5, m6)); // Ana

            profesorRepo.saveAll(List.of(p1, p2));
        };
    }

    // ========================
    // 🔧 MÉTODOS AUXILIARES
    // ========================

    private Estudiante crearEst(String nombre, String email, Profesor profesor) {
        Estudiante e = new Estudiante();
        e.setNombre(nombre);
        e.setEmail(email);
        e.setProfesor(profesor);
        return e;
    }

    private Materia crearMateria(String nombre) {
        Materia m = new Materia();
        m.setNombre(nombre);
        return m;
    }
}