package com.teste.rodrigo.testeb2w.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by not on 10/02/2017.
 */

public class Categorias {

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    @SerializedName("data")
    private List<Categoria> categorias;
}
