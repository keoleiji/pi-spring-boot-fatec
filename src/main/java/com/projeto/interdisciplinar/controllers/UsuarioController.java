package com.projeto.interdisciplinar.controllers;

import com.projeto.interdisciplinar.models.http.LoginRequest;
import com.projeto.interdisciplinar.models.Usuario;
import com.projeto.interdisciplinar.models.http.UsuarioCreateRequest;
import com.projeto.interdisciplinar.models.http.VincularReponsavelRequest;
import com.projeto.interdisciplinar.services.UsuarioService;
import com.projeto.interdisciplinar.utils.RetornoJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path="/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> login(@RequestBody @Valid final LoginRequest payload) {
        return ResponseEntity.ok(usuarioService.login(payload));
    }

    @GetMapping(value = "/usuario-matricula/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> listarUsuarioPorMatricula(@PathVariable("matricula") long matricula) {
        return ResponseEntity.ok(usuarioService.findById(matricula));
    }

    @GetMapping(value = "/usuarios-vinculados/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> listarUsuariosVinculados(@PathVariable("matricula") long matricula) {
        return ResponseEntity.ok(usuarioService.findVinculados(matricula));
    }

    @GetMapping(value = "/esqueci-senha/{matricula}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<?> alterarSenha(@PathVariable("matricula") long matricula) {
        RetornoJSON response = null;
        Usuario usuario = usuarioService.alterarSenha(matricula);
        if(usuario != null){
            sendEmail(usuario.getEmailUsuario(), "Senha Provisória da Plataforma", "Utilize essa senha para entrar na " +
                    "plataforma, e após o login, por favor altere-a.\n" +
                    "Senha: " + usuario.getSenhaUsuario());
            response = criaResponse(HttpStatus.OK, "Nova senha gerada! Cheque seu email para usá-la!");
        }
        else
            response = criaResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Falha na geração de nova senha!");
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @PostMapping(path="criar-usuario", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RetornoJSON> criarUsuario (@Valid @RequestBody UsuarioCreateRequest usuario) {
        RetornoJSON response = null;
        Usuario usuarioSalvo = usuarioService.save(usuario);
        if(usuarioSalvo != null){
            sendEmail(usuarioSalvo.getEmailUsuario(), "Senha Provisória da Plataforma", "Utilize essa senha para entrar na " +
                    "plataforma, e após o login, por favor altere-a.\n" +
                    "Senha: " + usuarioSalvo.getSenhaUsuario());
            response = criaResponse(HttpStatus.OK, "Usuário criado com sucesso! Cheque seu email para utilizar a senha provisória!");
        }
        else
            response = criaResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Falha na criação do usuário!");
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @PutMapping(path="alterar-usuario", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RetornoJSON> alterarUsuario(@Valid @RequestBody UsuarioCreateRequest usuarioDetalhes){
        RetornoJSON response = null;
        Usuario usuarioAlterado = usuarioService.update(usuarioDetalhes);
        if(usuarioAlterado != null)
            response = criaResponse(HttpStatus.OK, "Usuário alterado com sucesso!");
        else
            response = criaResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Falha na alteração do usuário!");
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @PutMapping(path="vincular-usuario", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RetornoJSON> vincularUsuario(@Valid @RequestBody VincularReponsavelRequest json){
        RetornoJSON response = null;
        Usuario usuarioAlterado = usuarioService.vincularUsuario(json);
        if(usuarioAlterado != null && json.getMatriculaResponsavel() != 0)
            response = criaResponse(HttpStatus.OK, "Usuário vinculado com sucesso!");
        else if(usuarioAlterado != null && json.getMatriculaResponsavel() == 0)
            response = criaResponse(HttpStatus.OK, "Usuário desvinculado com sucesso!");
        else if(usuarioAlterado == null && json.getMatriculaResponsavel() != 0)
            response = criaResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Falha na vinculação do usuário!");
        else
            response = criaResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Falha na desvinculação do usuário!");
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    private RetornoJSON criaResponse(HttpStatus status, String msg) {
        return new RetornoJSON(msg, status);
    }

    void sendEmail(String destinatario, String titulo, String texto) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(destinatario);

        msg.setSubject(titulo);
        msg.setText(texto);

        javaMailSender.send(msg);
    }

}
