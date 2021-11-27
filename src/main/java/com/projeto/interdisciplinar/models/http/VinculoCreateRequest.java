package com.projeto.interdisciplinar.models.http;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VinculoCreateRequest {

    @JsonProperty("matricula_usuario")
    private long matriculaUsuario;

    @JsonProperty("id_aula")
    private Integer idAula;

    public long getMatriculaUsuario() {
        return matriculaUsuario;
    }

    public void setMatriculaUsuario(long matriculaUsuario) {
        this.matriculaUsuario = matriculaUsuario;
    }

    public Integer getIdAula() {
        return idAula;
    }

    public void setIdAula(Integer idAula) {
        this.idAula = idAula;
    }
}
