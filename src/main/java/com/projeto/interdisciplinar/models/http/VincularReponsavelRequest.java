package com.projeto.interdisciplinar.models.http;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VincularReponsavelRequest {

    @JsonProperty("matricula_usuario")
    private long matriculaUsuario;

    @JsonProperty("matricula_responsavel")
    private long matriculaResponsavel;

    public long getMatriculaUsuario() {
        return matriculaUsuario;
    }

    public void setMatriculaUsuario(long matriculaUsuario) {
        this.matriculaUsuario = matriculaUsuario;
    }

    public long getMatriculaResponsavel() {
        return matriculaResponsavel;
    }

    public void setMatriculaResponsavel(long matriculaResponsavel) {
        this.matriculaResponsavel = matriculaResponsavel;
    }
}
