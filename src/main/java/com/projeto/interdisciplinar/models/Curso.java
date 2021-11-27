package com.projeto.interdisciplinar.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "curso")
public class Curso implements Serializable {

    @Id
    @Column(name="id_curso")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonProperty("id_curso")
    private Integer idCurso;

    @Column(name="nome_curso")
    @JsonProperty("nome_curso")
    private String nomeCurso;

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