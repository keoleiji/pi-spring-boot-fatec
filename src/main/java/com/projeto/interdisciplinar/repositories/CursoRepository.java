package com.projeto.interdisciplinar.repositories;
import com.projeto.interdisciplinar.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer>
{
}
