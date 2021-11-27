package com.projeto.interdisciplinar.services;

import com.projeto.interdisciplinar.models.Curso;
import com.projeto.interdisciplinar.models.http.VincularReponsavelRequest;
import com.projeto.interdisciplinar.repositories.CursoRepository;
import com.projeto.interdisciplinar.models.http.LoginRequest;
import com.projeto.interdisciplinar.models.Usuario;
import com.projeto.interdisciplinar.repositories.UsuarioRepository;
import com.projeto.interdisciplinar.models.http.UsuarioCreateRequest;
import com.projeto.interdisciplinar.utils.ResourceNotFoundException;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.print.DocFlavor.STRING;
import javax.swing.text.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CursoRepository cursoRepository;

    @PersistenceContext
    private EntityManager em;

    @RestController
    public class LoggingController {
    
        org.jboss.logging.Logger logger = LoggerFactory.logger(LoggingController.class);
    
        @RequestMapping("/")
        public String index() {
            logger.trace("A TRACE Message");
            logger.debug("A DEBUG Message");
            logger.info("An INFO Message");
            logger.warn("A WARN Message");
            logger.error("An ERROR Message");
    
            return "Howdy! Check out the Logs to see the output...";
        }
    }

    public List<?> login(LoginRequest loginRequest){
        String sha256hex = DigestUtils.sha256Hex(loginRequest.getSenhaUsuario());
        Query q = em.createQuery("SELECT u " +
                "FROM Usuario u " +
                "WHERE u.matriculaUsuario = :matriculaUsuario AND u.senhaUsuario = :senhaUsuario")
                .setParameter("matriculaUsuario", loginRequest.getMatriculaUsuario())
                .setParameter("senhaUsuario", sha256hex);
        List<?> results = q.getResultList();
        return results;
    }

    public Usuario findById(long matricula){
        Usuario usuario = new Usuario();
        Query q = em.createQuery("SELECT u " +
                "FROM Usuario u " +
                "WHERE u.matriculaUsuario = :matriculaUsuario")
                .setParameter("matriculaUsuario", matricula);
        try{
            usuario = (Usuario) q.getSingleResult();
        }
        catch(NoResultException e){
            System.out.println("Nenhum usuário encontrado");
        }

        return usuario;
    }

    public List<?> findVinculados(long matricula){
        Usuario usuario = usuarioService.findById(matricula);
        Query q = em.createQuery("SELECT u FROM Usuario u " +
                "WHERE u.matriculaResponsavel = :matriculaResponsavel")
                .setParameter("matriculaResponsavel", usuario);
        List<?> results = q.getResultList();
        return results;
    }

    public Usuario alterarSenha(long matricula){
        String senha = geraSenha();
        String sha256hex = DigestUtils.sha256Hex(senha);
        Usuario usuario = usuarioService.findById(matricula);
        usuario.setMatriculaResponsavel(usuario.getMatriculaResponsavel());
        usuario.setIdCurso(usuario.getIdCurso());
        usuario.setEmailUsuario(usuario.getEmailUsuario());
        usuario.setNomeUsuario(usuario.getNomeUsuario());
        usuario.setMatriculaUsuario(usuario.getMatriculaUsuario());
        usuario.setSenhaUsuario(sha256hex);
        Usuario usuarioAlterado = usuarioRepository.save(usuario);
        usuarioAlterado.setSenhaUsuario(senha);
        return usuarioAlterado;
    }

    public Usuario save(UsuarioCreateRequest usuarioCreateRequest){
        String senha = geraSenha();
        String sha256hex = DigestUtils.sha256Hex(senha);
        Usuario usuario = new Usuario();
        Curso curso = cursoRepository.findById(usuarioCreateRequest.getIdCurso())
                .orElseThrow(() -> new ResourceNotFoundException("Curso", "id", usuarioCreateRequest.getIdCurso()));
        usuario.setMatriculaUsuario(usuarioCreateRequest.getMatriculaUsuario());
        usuario.setNomeUsuario(usuarioCreateRequest.getNomeUsuario());
        usuario.setEmailUsuario(usuarioCreateRequest.getEmailUsuario());
        usuario.setSenhaUsuario(sha256hex);
        usuario.setMatriculaResponsavel(null);
        usuario.setIdCurso(curso);
        Usuario usuarioCriado = usuarioRepository.save(usuario);
        usuarioCriado.setSenhaUsuario(senha);
        return usuarioCriado;
    }

    public String geraSenha(){
        String caracteres = "ACBDEFGHIJKLabcdefghijkl123456789";
        String senha = "";
        int max = caracteres.length();
        int min = 0;
        int range = (max - min) + 1;
        for(int i = 0; i < 9; i++){
            Math.random();
            int rand = (int)(Math.random() * range) + min;
            senha += caracteres.charAt(rand);
        }
        return senha;
    }

    public Usuario update(UsuarioCreateRequest usuarioDetalhes){
        String sha256hex = DigestUtils.sha256Hex(usuarioDetalhes.getSenhaUsuario());
        Usuario usuario = usuarioService.findById(usuarioDetalhes.getMatriculaUsuario());
        Curso curso = cursoRepository.findById(usuarioDetalhes.getIdCurso())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "matricula", usuarioDetalhes.getMatriculaUsuario()));;
        usuario.setMatriculaResponsavel(usuario.getMatriculaResponsavel());
        if(usuarioDetalhes.getNomeUsuario() != null)
            usuario.setNomeUsuario(usuarioDetalhes.getNomeUsuario());
        else usuario.setNomeUsuario(usuario.getNomeUsuario());
        if(usuarioDetalhes.getEmailUsuario() != null)
            usuario.setEmailUsuario(usuarioDetalhes.getEmailUsuario());
        else usuario.setEmailUsuario(usuario.getEmailUsuario());
        if(usuarioDetalhes.getSenhaUsuario() != null)
            usuario.setSenhaUsuario(sha256hex);
        else usuario.setSenhaUsuario(usuario.getSenhaUsuario());
        if(usuarioDetalhes.getIdCurso() != null)
            usuario.setIdCurso(curso);
        else usuario.setIdCurso(usuario.getIdCurso());
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return usuarioAtualizado;
    }

    public Usuario vincularUsuario(VincularReponsavelRequest json){
        Usuario usuario = usuarioService.findById(json.getMatriculaUsuario());
        if(json.getMatriculaResponsavel() > 0){
            Usuario usuarioResponsavel = usuarioService.findById(json.getMatriculaResponsavel());
            usuario.setMatriculaResponsavel(usuarioResponsavel);
        } else {
            usuario.setMatriculaResponsavel(null);
        }
        usuario.setNomeUsuario(usuario.getNomeUsuario());
        usuario.setEmailUsuario(usuario.getEmailUsuario());
        usuario.setSenhaUsuario(usuario.getSenhaUsuario());
        usuario.setIdCurso(usuario.getIdCurso());
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return usuarioAtualizado;
    }

}
