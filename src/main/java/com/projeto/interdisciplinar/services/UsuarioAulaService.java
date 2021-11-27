package com.projeto.interdisciplinar.services;

import com.projeto.interdisciplinar.models.Aula;
import com.projeto.interdisciplinar.models.Usuario;
import com.projeto.interdisciplinar.models.UsuarioAula;
import com.projeto.interdisciplinar.models.http.VinculoCreateRequest;
import com.projeto.interdisciplinar.repositories.AulaRepository;
import com.projeto.interdisciplinar.repositories.UsuarioAulaRepository;
import com.projeto.interdisciplinar.repositories.UsuarioRepository;
import com.projeto.interdisciplinar.models.http.MateriasVinculadasMatriculaResponse;
import com.projeto.interdisciplinar.utils.ResourceNotFoundException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
import java.util.List;

import org.hibernate.query.Query;

@Service
public class UsuarioAulaService {

    @Autowired
    private UsuarioAulaRepository usuarioAulaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AulaRepository aulaRepository;

    @PersistenceContext
    private EntityManager em;

    public List<MateriasVinculadasMatriculaResponse> findVinculadosMatricula(long matricula){
        List<MateriasVinculadasMatriculaResponse> results = em.createQuery(
                "SELECT um.idVinculacao as idVinculacao, a.idMateria as idMateria, a.idAula as idAula, " +
                "c.idCurso as idCurso, c.nomeCurso as nomeCurso, um.idVinculacao as idVinculacao " +
                "FROM Curso c " +
                "JOIN Aula a ON a.idCurso = c.idCurso " +
                "JOIN Materia m on m.idMateria = a.idMateria " +
                "JOIN UsuarioAula um ON um.idAula = a.idAula " +
                "JOIN Usuario u ON u.matriculaUsuario = um.matriculaUsuario " +
                "WHERE u.matriculaUsuario = :matriculaUsuario ")
                .setParameter("matriculaUsuario", matricula)
                .unwrap(Query.class)
                .setResultTransformer(Transformers.aliasToBean(MateriasVinculadasMatriculaResponse.class))
                .getResultList();
        return results;
    }

    public UsuarioAula save(VinculoCreateRequest vinculo){
        UsuarioAula usuarioAula = new UsuarioAula();
        Aula aula = aulaRepository.findById(vinculo.getIdAula())
                .orElseThrow(() -> new ResourceNotFoundException("Aula", "idMateria", vinculo.getIdAula()));
        Usuario usuario = usuarioService.findById(vinculo.getMatriculaUsuario());

        usuarioAula.setIdAula(aula);
        usuarioAula.setMatriculaUsuario(usuario);

        return usuarioAulaRepository.save(usuarioAula);
    }

    public String delete(Integer id){
        try {
            UsuarioAula usuarioAula = usuarioAulaRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("UsuarioAula", "id", id));
            usuarioAulaRepository.delete(usuarioAula);
            return "OK";
        }
        catch(Exception e){
            return e.getMessage();
        }
    }

}
