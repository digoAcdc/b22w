package com.teste.rodrigo.testeb2w;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teste.rodrigo.testeb2w.Model.Produto;
import com.teste.rodrigo.testeb2w.Model.Produtos;
import com.teste.rodrigo.testeb2w.adapter.ListaProdutosPorCategoriaAdaper;
import com.teste.rodrigo.testeb2w.adapter.ProdutosMaisVendidosAdapter;
import com.teste.rodrigo.testeb2w.api.ApiService;
import com.teste.rodrigo.testeb2w.api.IApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListaProdutosCategoriaActivity extends AppCompatActivity {

    private ImageView imv_produto;
    private TextView txt_nm_produto;
    private TextView txtPrecoDe;
    private TextView txtPrecoPor;
    private TextView txtDescricao;
    private RecyclerView rvListProdutos;
    private int idCategoria;
    private boolean loading = true;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private Produtos produtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos_categoria);

        rvListProdutos = (RecyclerView)findViewById(R.id.rvListProdutos);
        rvListProdutos.setLayoutManager(new LinearLayoutManager(ListaProdutosCategoriaActivity.this));
        rvListProdutos.setHasFixedSize(true);

        final LinearLayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(this);
        rvListProdutos.setLayoutManager(mLayoutManager);

        imv_produto = (ImageView) findViewById(R.id.imv_produto);
        txt_nm_produto = (TextView) findViewById(R.id.txt_nm_produto);
        txtPrecoDe = (TextView) findViewById(R.id.txtPrecoDe);
        txtPrecoPor = (TextView) findViewById(R.id.txtPrecoPor);
        txtDescricao = (TextView) findViewById(R.id.txtDescricao);

        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        if (params != null) {
            String id = params.getString("id");
            String descCategoria = params.getString("descCategoria");

            getSupportActionBar().setTitle(descCategoria);

            idCategoria = Integer.valueOf(id);

            getListaProdutosPorCategoria(idCategoria, 0, 20);
        }

        rvListProdutos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy > 0){
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            loading = false;
                            Log.v("...", "Last Item Wow !");
                            //Do pagination.. i.e. fetch new data
                            /*produtos.getTotal()
                            getListaProdutosPorCategoria(idCategoria, )*/
                        }
                    }
                }
            }
        });

    }



    public void getListaProdutosPorCategoria(int idCategoria, int offset, int limit)
    {
        ApiService api = new ApiService();
        Retrofit retrofit = api.getInstance();
        IApiService iApiService = retrofit.create(IApiService.class);
        Call<Produtos> call = iApiService.listProdutos(0, 20, idCategoria);

        call.enqueue(new Callback<Produtos>() {
            @Override
            public void onResponse(Call<Produtos> call, Response<Produtos> response) {
                produtos = response.body();
                rvListProdutos.setAdapter(new ListaProdutosPorCategoriaAdaper(ListaProdutosCategoriaActivity.this, response.body().getProdutos(), onClickItemPrododutoCategoria()));

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListaProdutosCategoriaActivity.this);
                // for horizontal
                //linearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);
                // for vertical
                linearLayoutManager.setOrientation(LinearLayout.VERTICAL);

                rvListProdutos.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(Call<Produtos> call, Throwable t) {

            }
        });
    }



    private ListaProdutosPorCategoriaAdaper.ProdutoOnClickListener onClickItemPrododutoCategoria(){
        return new ListaProdutosPorCategoriaAdaper.ProdutoOnClickListener(){

            @Override
            public void OnClickitemLista(View view, int index) {
                TextView tvId = (TextView) view.findViewById(R.id.txtIdProduto);
                Intent i = new Intent(ListaProdutosCategoriaActivity.this, ProdutoActivity.class);
                i.putExtra("id", tvId.getText());
                i.putExtra("produtoOuCategoria", "categoria");
                startActivity(i);
            }
        };
    }


}
