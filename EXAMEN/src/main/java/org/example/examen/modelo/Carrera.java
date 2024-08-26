package org.example.examen.modelo;

import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "carrera", schema = "materia_espe")
public class Carrera {
    @EmbeddedId
    private CarreraId id;

    @Column(name = "NOMBRE_CARRERA", length = 45)
    private String nombreCarrera;

    @Column(name = "NUM_NIVEL")
    private Integer numNivel;



    public CarreraId getId() {
        return id;
    }

    public void setId(CarreraId id) {
        this.id = id;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public Integer getNumNivel() {
        return numNivel;
    }

    public void setNumNivel(Integer numNivel) {
        this.numNivel = numNivel;
    }



}