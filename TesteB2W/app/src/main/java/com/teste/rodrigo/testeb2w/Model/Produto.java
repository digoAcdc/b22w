package com.teste.rodrigo.testeb2w.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by not on 09/02/2017.
 */


public class Produto {
    private long id;
    private String nome;
    private String urlImagem;
    private String descricao;
    private double precoDe;
    private double precoPor;
    private Categoria categoria;




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoDe() {
        return precoDe;
    }

    public void setPrecoDe(double precoDe) {
        this.precoDe = precoDe;
    }

    public double getPrecoPor() {
        return precoPor;
    }

    public void setPrecoPor(double precoPor) {
        this.precoPor = precoPor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
