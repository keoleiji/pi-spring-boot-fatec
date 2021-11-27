package com.projeto.interdisciplinar.controllers;

import com.projeto.interdisciplinar.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path="/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> listarCursos() {
        return ResponseEntity.ok(cursoService.findAll());
    }

}