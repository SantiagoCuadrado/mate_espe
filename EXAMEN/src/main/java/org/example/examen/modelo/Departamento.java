package org.example.examen.modelo;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "departamento", schema = "materia_espe")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTAMENTO_ID")
    private int id;

    @Column(name = "NOMBRE_DEPARTAMENTO")
    private String nombre;

    @Column(name = "ACRO_DEPARTAMENTO")
    private String acronimo;

    // Constructor por defecto
    public Departamento() {
    }

    // Constructor con argumentos
    public Departamento(int id, String nombre, String acronimo) {
        this.id = id;
        this.nombre = nombre;
        this.acronimo = acronimo;
    }

    // Getters y Setters
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getAcronimo(){
        return acronimo;
    }
    public void setAcronimo(String acronimo){
        this.acronimo = acronimo;
    }
}