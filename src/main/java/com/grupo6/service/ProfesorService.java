package com.grupo6.service;

import com.grupo6.model.entity.Estudiante;
import com.grupo6.model.entity.Profesor;
import com.grupo6.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {

    private final ProfesorRepository repository;

    public ProfesorService(ProfesorRepository repository) {
        this.repository = repository;
    }

    public List<Profesor> obtenerTodos() {
        return repository.findAll();
    }

    public Profesor obtenerPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Profesor guardar(Profesor profesor) {
        return repository.save(profesor);
    }

    public List<Estudiante> obtenerEstudiantesPorProfesor(Long id) {
        return obtenerPorId(id).getEstudiantes();
    }
}