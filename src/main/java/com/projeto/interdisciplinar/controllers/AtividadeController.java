package com.projeto.interdisciplinar.controllers;

import com.projeto.interdisciplinar.models.Atividade;
import com.projeto.interdisciplinar.models.http.AtividadeCreateRequest;
import com.projeto.interdisciplinar.services.AtividadeService;
import com.projeto.interdisciplinar.utils.RetornoJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path="/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> listarAtividadePorMateria(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(atividadeService.findByMateria(id));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RetornoJSON> criarAtividade (@Valid @RequestBody AtividadeCreateRequest atividade) {
        RetornoJSON response = null;
        Atividade atividadeSalva = atividadeService.save(atividade);
        if(atividadeSalva != null)
            response = criaResponse(HttpStatus.OK, "Atividade criada com sucesso!");
        else
            response = criaResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Falha na criação da atividade!");
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RetornoJSON> alterarAtividade(@Valid @RequestBody AtividadeCreateRequest atividadeDetalhes){
        RetornoJSON response = null;
        Atividade atividadeAlterada = atividadeService.update(atividadeDetalhes);
        if(atividadeAlterada != null)
            response = criaResponse(HttpStatus.OK, "Atividade alterada com sucesso!");
        else
            response = criaResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Falha na alteração da atividade!");
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RetornoJSON> deletarAtividade(@PathVariable(value = "id") Integer id) {
        RetornoJSON response = null;
        String status = atividadeService.delete(id);
        if(status == "OK")
            response = criaResponse(HttpStatus.OK, "Atividade deletada com sucesso!");
        else
            response = criaResponse(HttpStatus.HTTP_VERSION_NOT_SUPPORTED, "Falha na remoção da atividade!");
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    private RetornoJSON criaResponse(HttpStatus status, String msg) {
        return new RetornoJSON(msg, status);
    }

}
