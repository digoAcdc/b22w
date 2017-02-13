package com.teste.rodrigo.testeb2w.Model;

import java.io.Serializable;

/**
 * Created by not on 09/02/2017.
 */

public class Categoria {
    private long id;
    private String descricao;
    private String urlImagem;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }
}
