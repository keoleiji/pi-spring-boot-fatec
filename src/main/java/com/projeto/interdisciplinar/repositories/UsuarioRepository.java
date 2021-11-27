package com.projeto.interdisciplinar.repositories;
import com.projeto.interdisciplinar.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>
{
}
