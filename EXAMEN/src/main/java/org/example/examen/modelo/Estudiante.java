package org.example.examen.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "estudiante", schema = "materia_espe", indexes = {
        @Index(name = "ESTUDIANTE_USUARIO_idx", columnList = "USUARIO_ID"),
        @Index(name = "ESTUDIANTE_CARRERA_idx", columnList = "CARRERA_ID")
})
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ESTUDIANTE_ID", nullable = false)
    private Integer id;

    @Column(name = "USUARIO_ID", nullable = false)
    private Integer usuarioId;

    @Column(name = "NOMBRE_ESTUDIANTE", length = 45)
    private String nombreEstudiante;

    @Column(name = "APELLIDO_ESTUDIANTE", length = 45)
    private String apellidoEstudiante;

    @Column(name = "CARRERA_ID", nullable = false)
    private Integer carreraId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getApellidoEstudiante() {
        return apellidoEstudiante;
    }

    public void setApellidoEstudiante(String apellidoEstudiante) {
        this.apellidoEstudiante = apellidoEstudiante;
    }

    public Integer getCarreraId() {
        return carreraId;
    }

    public void setCarreraId(Integer carreraId) {
        this.carreraId = carreraId;
    }

}