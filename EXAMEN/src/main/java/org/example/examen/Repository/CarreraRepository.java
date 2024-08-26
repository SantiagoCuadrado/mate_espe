package org.example.examen.Repository;

import org.example.examen.modelo.Carrera;
import org.example.examen.modelo.CarreraId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, CarreraId> {
    // Puedes agregar métodos personalizados aquí si es necesario
    // Consulta personalizada para encontrar Carrera por carreraId
    @Query("SELECT c FROM Carrera c WHERE c.id.carreraId = :carreraId")
    Optional<Carrera> findByCarreraId(@Param("carreraId") Integer carreraId);
}
