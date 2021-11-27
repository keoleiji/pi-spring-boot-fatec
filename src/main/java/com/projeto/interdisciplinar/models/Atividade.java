package com.projeto.interdisciplinar.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "atividade")
public class Atividade implements Serializable {

    @Id
    @Column(name="id_atividade")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonProperty("id_atividade")
    private Integer idAtividade;

    @ManyToOne(optional = false)
    @JoinColumn(name="id_aula")
    @JsonProperty("id_aula")
    private Aula idAula;

    @ManyToOne(optional = false)
    @JoinColumn(name="matricula_usuario")
    @JsonProperty("matricula_usuario")
    private Usuario matriculaUsuario;

    @Column(name="data_cadastro")
    @JsonProperty("data_cadastro")
    private Date dataCadastro;

    @Column(name="data_final")
    @JsonProperty("data_final")
    private Date dataFinal;

    @Column(name="titulo_atividade")
    @JsonProperty("titulo_atividade")
    private String tituloAtividade;

    @Column(name="descricao_atividade")
    @JsonProperty("descricao_atividade")
    private String descricaoAtividade;

    @Column(name="fontes_atividade")
    @JsonProperty("fontes_atividade")
    private String fontesAtividade;

    public Integer getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(Integer idAtividade) {
        this.idAtividade = idAtividade;
    }

    public Aula getIdAula() { return idAula; }

    public void setIdAula (Aula idAula) {
        this.idAula = idAula;
    }

    public Usuario getMatriculaUsuario() {
        return matriculaUsuario;
    }

    public void setMatriculaUsuario(Usuario matriculaUsuario) {
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
