package org.example.examen.service;

import org.example.examen.modelo.Carrera;
import org.example.examen.Repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    // Método para obtener una carrera por carreraId
    public Carrera getCarreraById(Integer carreraId) {
        return carreraRepository.findByCarreraId(carreraId)
                .orElseThrow(() -> new IllegalArgumentException("Carrera no encontrada"));
    }
}
