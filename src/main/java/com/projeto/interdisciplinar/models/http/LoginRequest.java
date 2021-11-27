package com.projeto.interdisciplinar.models.http;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {

    @JsonProperty("matricula_usuario")
    private long matriculaUsuario;

    @JsonProperty("senha_usuario")
    private String senhaUsuario;

    public long getMatriculaUsuario() {
        return matriculaUsuario;
    }

    public void setMatriculaUsuario(long matriculaUsuario) {
        this.matriculaUsuario = matriculaUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }
}
