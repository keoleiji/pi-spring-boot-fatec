package com.projeto.interdisciplinar.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="aux_usuario_aula")
public class UsuarioAula implements Serializable {

    @Id
    @Column(name="id_vinculacao")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonProperty("id_vinculacao")
    private Integer idVinculacao;

    @ManyToOne(optional = false)
    @JoinColumn(name="matricula_usuario")
    @JsonProperty("matricula_usuario")
    private Usuario matriculaUsuario;

    @ManyToOne(optional = false)
    @JoinColumn(name="id_aula")
    @JsonProperty("id_aula")
    private Aula idAula;

    public Integer getIdVinculacao() {
        return idVinculacao;
    }

    public void setIdVinculacao(Integer idVinculacao) {
        this.idVinculacao = idVinculacao;
    }

    public Usuario getMatriculaUsuario() {
        return matriculaUsuario;
    }

    public void setMatriculaUsuario(Usuario matriculaUsuario) {
        this.matriculaUsuario = matriculaUsuario;
    }

    public Aula getIdAula() {
        return idAula;
    }

    public void setIdAula(Aula idAula) {
        this.idAula = idAula;
    }
}
