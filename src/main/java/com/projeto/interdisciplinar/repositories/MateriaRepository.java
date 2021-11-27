package com.projeto.interdisciplinar.repositories;

import com.projeto.interdisciplinar.models.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<Materia, Integer> {
}
