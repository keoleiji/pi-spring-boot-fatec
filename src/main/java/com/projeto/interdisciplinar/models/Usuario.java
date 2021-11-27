package com.projeto.interdisciplinar.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @Column(name="matricula_usuario")
    @JsonProperty("matricula_usuario")
    private long matriculaUsuario;

    @ManyToOne(optional = true)
    @JoinColumn(name="matricula_responsavel")
    @JsonProperty("matricula_responsavel")
    private Usuario matriculaResponsavel;

    @ManyToOne(optional = false)
    @JoinColumn(name="id_curso")
    @JsonProperty("id_curso")
    private Curso idCurso;

    @Column(name="nome_usuario")
    @JsonProperty("nome_usuario")
    private String nomeUsuario;

    @Column(name="senha_usuario")
    @JsonProperty("senha_usuario")
    private String senhaUsuario;

    @Column(name="email_usuario")
    @JsonProperty("email_usuario")
    private String emailUsuario;

    public long getMatriculaUsuario() {
        return matriculaUsuario;
    }

    public void setMatriculaUsuario(long matriculaUsuario) {
        this.matriculaUsuario = matriculaUsuario;
    }

    public Usuario getMatriculaResponsavel() {
        return matriculaResponsavel;
    }

    public void setMatriculaResponsavel(Usuario matriculaResponsavel) {
        this.matriculaResponsavel = matriculaResponsavel;
    }

    public Curso getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Curso idCurso) {
        this.idCurso = idCurso;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }
}
