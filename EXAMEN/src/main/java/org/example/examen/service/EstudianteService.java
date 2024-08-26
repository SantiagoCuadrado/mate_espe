package org.example.examen.service;

import org.example.examen.modelo.Carrera;
import org.example.examen.modelo.Estudiante;
import org.example.examen.Repository.CarreraRepository;
import org.example.examen.Repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstudianteService {
    @Autowired
    private EstudianteRepository estudianteRepository;

    // Método para obtener un estudiante por ID
    public Estudiante getEstudianteById(Integer estudianteId) {
        return estudianteRepository.findById(estudianteId)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));
    }

}
