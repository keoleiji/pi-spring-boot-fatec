package com.projeto.interdisciplinar.utils;

import org.springframework.http.HttpStatus;

public class RetornoJSON {
    private String mensagem;
    private HttpStatus httpStatus;


    public RetornoJSON(String mensagem, HttpStatus httpStatus){
        this.mensagem = mensagem;
        this.httpStatus = httpStatus;
    }

    public RetornoJSON(HttpStatus httpStatus){
        this.mensagem = "Ocorreu um erro durante a requisição. Caso persista o erro, acione a equipe responsável.";
        this.httpStatus = httpStatus;
    }

    public String getMensagem(){
        return mensagem;
    }

    public void setMensagem(String mensagem){
        this.mensagem = mensagem;
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus){
        this.httpStatus = httpStatus;
    }
}
