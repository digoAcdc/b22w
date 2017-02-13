package com.teste.rodrigo.testeb2w.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by not on 10/02/2017.
 */

public class Produtos {

    @SerializedName("data")
    private List<Produto> produtos;
    private int offset;
    private int total;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
