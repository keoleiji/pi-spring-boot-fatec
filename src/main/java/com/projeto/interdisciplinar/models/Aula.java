package com.projeto.interdisciplinar.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="aula")
public class Aula {

    @Id
    @Column(name="id_aula")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonProperty("id_aula")
    private Integer idAula;

    @ManyToOne(optional = false)
    @JoinColumn(name="id_curso")
    @JsonProperty("id_curso")
    private Curso idCurso;

    @ManyToOne(optional = false)
    @JoinColumn(name="id_materia")
    @JsonProperty("id_materia")
    private Materia idMateria;

    @Column(name="semestre")
    @JsonProperty("semestre")
    private Integer semestre;

    public Integer getIdAula() {
        return idAula;
    }

    public void setIdAula(Integer idAula) {
        this.idAula = idAula;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }

    public Materia getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }
}
