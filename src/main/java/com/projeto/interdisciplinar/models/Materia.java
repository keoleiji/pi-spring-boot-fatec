package com.projeto.interdisciplinar.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "materia")
public class Materia implements Serializable {

    @Id
    @Column(name="id_materia")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonProperty("id_materia")
    private Integer idMateria;

    @Column(name="nome_materia")
    @JsonProperty("nome_materia")
    private String nomeMateria;

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public String getNomeMateria() {
        return nomeMateria;
    }

    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria = nomeMateria;
    }

}
