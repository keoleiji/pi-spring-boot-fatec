package com.projeto.interdisciplinar.services;

import com.projeto.interdisciplinar.models.Curso;
import com.projeto.interdisciplinar.models.http.MateriasVinculadasCursoResponse;
import com.projeto.interdisciplinar.models.http.MateriasVinculadasMatriculaResponse;
import com.projeto.interdisciplinar.utils.ResourceNotFoundException;
import com.projeto.interdisciplinar.models.Materia;
import com.projeto.interdisciplinar.repositories.MateriaRepository;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    @PersistenceContext
    private EntityManager em;

    public List<MateriasVinculadasCursoResponse> findByIdCurso(Integer id){
        List<MateriasVinculadasCursoResponse> results = em.createQuery(
                "SELECT a.semestre as semestre, a.idMateria as idMateria,  a.idAula as idAula, " +
                "c.idCurso as idCurso, c.nomeCurso as nomeCurso " +
                "FROM Curso c " +
                "JOIN Aula a ON a.idCurso = c.idCurso " +
                "JOIN Materia m on m.idMateria = a.idMateria " +
                "WHERE c.idCurso = :idCurso ")
                .setParameter("idCurso", id)
                .unwrap(Query.class)
                .setResultTransformer(Transformers.aliasToBean(MateriasVinculadasMatriculaResponse.class))
                .getResultList();
        return results;
    }

}
