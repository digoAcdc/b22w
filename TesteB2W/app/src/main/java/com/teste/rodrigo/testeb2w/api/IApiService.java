package com.teste.rodrigo.testeb2w.api;

import com.teste.rodrigo.testeb2w.Model.Banner;
import com.teste.rodrigo.testeb2w.Model.Banners;
import com.teste.rodrigo.testeb2w.Model.Categoria;
import com.teste.rodrigo.testeb2w.Model.Categorias;
import com.teste.rodrigo.testeb2w.Model.Produto;
import com.teste.rodrigo.testeb2w.Model.Produtos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by not on 09/02/2017.
 */

public interface IApiService {

    @GET("banner")
    Call<Banners> listBanners();

    @GET("categoria")
    Call<Categorias> listCategorias();

    @GET("produto")
    Call<Produtos> listProdutos(@Query("offset") int offset, @Query("limit") int limit, @Query("categoriaId") int categoriaId);

    @GET("produto/maisvendidos")
    Call<Produtos> listProdutosMaisVendidos();

    @GET("produto/{produtoId}")
    Call<Produto> listProdutosPorId(@Path("produtoId") long produtoId);

    @POST("produto/")
    Call<Produto> CreateProduto(@Query("produtoId") Produto produtoId);

}
