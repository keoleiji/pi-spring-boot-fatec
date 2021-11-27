package com.projeto.interdisciplinar.services;

import com.projeto.interdisciplinar.models.Atividade;
import com.projeto.interdisciplinar.models.Aula;
import com.projeto.interdisciplinar.models.Usuario;
import com.projeto.interdisciplinar.models.http.AtividadeCreateRequest;
import com.projeto.interdisciplinar.repositories.AtividadeRepository;
import com.projeto.interdisciplinar.repositories.AulaRepository;
import com.projeto.interdisciplinar.repositories.UsuarioRepository;
import com.projeto.interdisciplinar.utils.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @PersistenceContext
    private EntityManager em;

    public List<Atividade> findByMateria(Integer id){
        Query q = em.createQuery("SELECT at FROM Atividade at " +
                "JOIN Aula au ON au.idAula = at.idAula " +
                "WHERE au.idAula = :idAula " +
                "ORDER BY at.dataFinal")
                .setParameter("idAula", id);
        List<Atividade> results = q.getResultList();
        return results;
    }

    public Atividade save(AtividadeCreateRequest atividadeCreate){
        Atividade atividade = new Atividade();
        atividade.setDataFinal(atividadeCreate.getDataFinal());
        atividade.setDataCadastro(atividadeCreate.getDataCadastro());
        atividade.setFontesAtividade(atividadeCreate.getFontesAtividade());
        atividade.setDescricaoAtividade(atividadeCreate.getDescricaoAtividade());
        atividade.setTituloAtividade(atividadeCreate.getTituloAtividade());

        Usuario usuario = usuarioService.findById(atividadeCreate.getMatriculaUsuario());
        Aula aula = aulaRepository.findById(atividadeCreate.getIdAula())
                .orElseThrow(() -> new ResourceNotFoundException("Aula", "id", atividadeCreate.getIdAula()));;

        atividade.setMatriculaUsuario(usuario);
        atividade.setIdAula(aula);

        return atividadeRepository.save(atividade);
    }

    public Atividade update(AtividadeCreateRequest atividadeDetalhes){
        Usuario usuario = usuarioService.findById(atividadeDetalhes.getMatriculaUsuario());
        Atividade atividade = atividadeRepository.findById(atividadeDetalhes.getIdAtividade())
                .orElseThrow(() -> new ResourceNotFoundException("Atividade", "id", atividadeDetalhes.getIdAtividade()));
        atividade.setTituloAtividade(atividadeDetalhes.getTituloAtividade());
        atividade.setDescricaoAtividade(atividadeDetalhes.getDescricaoAtividade());
        atividade.setFontesAtividade(atividadeDetalhes.getFontesAtividade());
        atividade.setDataCadastro(atividade.getDataCadastro());
        atividade.setDataFinal(atividadeDetalhes.getDataFinal());
        atividade.setIdAula(atividade.getIdAula());
        atividade.setMatriculaUsuario(usuario);
        Atividade atividadeAtualizada = atividadeRepository.save(atividade);
        return atividadeAtualizada;
    }

    public String delete(Integer id){
        try {
            Atividade atividade = atividadeRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Atividade", "id", id));
            atividadeRepository.delete(atividade);
            return "OK";
        }
        catch(Exception e){
            return e.getMessage();
        }
    }

}
