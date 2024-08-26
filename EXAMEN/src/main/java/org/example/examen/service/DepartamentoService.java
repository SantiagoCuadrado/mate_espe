package org.example.examen.service;

import org.example.examen.modelo.Departamento;
import org.example.examen.Repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    // Método para obtener un departamento por ID
    public Departamento getDepartamentoById(Integer id) {
        Optional<Departamento> departamentoOpt = departamentoRepository.findById(id);
        if (departamentoOpt.isPresent()) {
            return departamentoOpt.get();
        } else {
            throw new IllegalArgumentException("Departamento no encontrado");
        }
    }
}
