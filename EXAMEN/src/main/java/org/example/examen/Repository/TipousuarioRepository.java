package org.example.examen.Repository;

import org.example.examen.modelo.Tipousuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipousuarioRepository extends JpaRepository<Tipousuario, Integer> {
}