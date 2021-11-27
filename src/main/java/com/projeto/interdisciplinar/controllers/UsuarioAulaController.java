package com.projeto.interdisciplinar.controllers;

import com.projeto.interdisciplinar.models.UsuarioAula;
import com.projeto.interdisciplinar.models.http.VinculoCreateRequest;
import com.projeto.interdisciplinar.services.UsuarioAulaService;
import com.projeto.interdisciplinar.utils.RetornoJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path="/aulas-vinculadas")
public class UsuarioAulaController {

    @Autowired
    private UsuarioAulaService usuarioAulaService;

    @GetMapping(value = "/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> listarAulasVinculadasMatricula(@PathVariable("matricula") long matricula) {
        return ResponseEntity.ok(usuarioAulaService.findVinculadosMatricula(matricula));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> vincularAulaMatricula(@Valid @RequestBody VinculoCreateRequest info) {
        RetornoJSON response = null;
        UsuarioAula usuarioAulaSalva = usuarioAulaService.save(info);
        if(usuarioAulaSalva != null)
            response = criaResponse(HttpStatus.OK, "Matéria vinculada ao usuário com sucesso!");
        else
            response = criaResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Falha na vinculação da matéria com o usuário!");
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @DeleteMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> desvincularAulaMatricula(@PathVariable("id") Integer id) {
        RetornoJSON response = null;
        String status = usuarioAulaService.delete(id);
        if(status == "OK")
            response = criaResponse(HttpStatus.OK, "Matéria desvinculada do usuário com sucesso!");
        else
            response = criaResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Falha na desvinculação da matéria com o usuário!");
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    private RetornoJSON criaResponse(HttpStatus status, String msg) {
        return new RetornoJSON(msg, status);
    }

}
