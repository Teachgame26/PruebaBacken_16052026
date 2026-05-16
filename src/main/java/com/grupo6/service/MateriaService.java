package com.grupo6.service;

import com.grupo6.model.entity.Materia;
import com.grupo6.repository.MateriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService {

    private final MateriaRepository repository;

    public MateriaService(MateriaRepository repository) {
        this.repository = repository;
    }

    public List<Materia> obtenerTodas() {
        return repository.findAll();
    }

    public Materia guardar(Materia materia) {
        return repository.save(materia);
    }
}
