package com.projeto.interdisciplinar.models.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projeto.interdisciplinar.models.Aula;
import com.projeto.interdisciplinar.models.Usuario;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;

public class AtividadeCreateRequest {

    @JsonProperty("id_atividade")
    private Integer idAtividade;

    @JsonProperty("id_aula")
    private Integer idAula;

    @JsonProperty("matricula_usuario")
    private long matriculaUsuario;

    @JsonProperty("data_cadastro")
    private Date dataCadastro;

    @JsonProperty("data_final")
    private Date dataFinal;

    @JsonProperty("titulo_atividade")
    private String tituloAtividade;

    @JsonProperty("descricao_atividade")
    private String descricaoAtividade;

    @JsonProperty("fontes_atividade")
    private String fontesAtividade;

    public Integer getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(Integer idAtividade) {
        this.idAtividade = idAtividade;
    }

    public Integer getIdAula() {
        return idAula;
    }

    public void setIdAula(Integer idAula) {
        this.idAula = idAula;
    }

    public long getMatriculaUsuario() {
        return matriculaUsuario;
    }

    public void setMatriculaUsuario(long matriculaUsuario) {
        this.matriculaUsuario = matriculaUsuario;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getTituloAtividade() {
        return tituloAtividade;
    }

    public void setTituloAtividade(String tituloAtividade) {
        this.tituloAtividade = tituloAtividade;
    }

    public String getDescricaoAtividade() {
        return descricaoAtividade;
    }

    public void setDescricaoAtividade(String descricaoAtividade) {
        this.descricaoAtividade = descricaoAtividade;
    }

    public String getFontesAtividade() {
        return fontesAtividade;
    }

    public void setFontesAtividade(String fontesAtividade) {
        this.fontesAtividade = fontesAtividade;
    }
}
