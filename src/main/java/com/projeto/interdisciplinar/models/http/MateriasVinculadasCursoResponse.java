package com.projeto.interdisciplinar.models.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projeto.interdisciplinar.models.Materia;

public class MateriasVinculadasCursoResponse {

    @JsonProperty("semestre")
    private Integer semestre;

    @JsonProperty("id_materia")
    private Materia idMateria;

    @JsonProperty("id_aula")
    private Integer idAula;

    @JsonProperty("id_curso")
    private Integer idCurso;

    @JsonProperty("nome_curso")
    private String nomeCurso;

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }

    public Integer getIdAula() {
        return idAula;
    }

    public void setIdAula(Integer idAula) {
        this.idAula = idAula;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }
}
