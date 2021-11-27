package com.projeto.interdisciplinar.services;

import com.projeto.interdisciplinar.utils.ResourceNotFoundException;
import com.projeto.interdisciplinar.models.Curso;
import com.projeto.interdisciplinar.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> findAll(){
        return cursoRepository.findAll();
    }

}
