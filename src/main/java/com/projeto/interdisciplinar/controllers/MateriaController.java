package com.projeto.interdisciplinar.controllers;

import com.projeto.interdisciplinar.models.Materia;
import com.projeto.interdisciplinar.services.MateriaService;
import com.projeto.interdisciplinar.utils.RetornoJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path="/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> listarMateriaPorIdCurso(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(materiaService.findByIdCurso(id));
    }

}