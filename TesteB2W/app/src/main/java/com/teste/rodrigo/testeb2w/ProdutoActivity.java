package com.teste.rodrigo.testeb2w;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teste.rodrigo.testeb2w.Model.Produto;
import com.teste.rodrigo.testeb2w.api.ApiService;
import com.teste.rodrigo.testeb2w.api.IApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProdutoActivity extends AppCompatActivity {

    private ImageView imv_produto;
    private TextView txt_nm_produto;
    private TextView txtPrecoDe;
    private TextView txtPrecoPor;
    private TextView txtDescricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        imv_produto = (ImageView) findViewById(R.id.imv_produto);
        txt_nm_produto = (TextView) findViewById(R.id.txt_nm_produto);
        txtPrecoDe = (TextView) findViewById(R.id.txtPrecoDe);
        txtPrecoPor = (TextView) findViewById(R.id.txtPrecoPor);
        txtDescricao = (TextView) findViewById(R.id.txtDescricao);

        Intent intent = getIntent();
        Bundle params = intent.getExtras();

        if (params != null) {
            String id = params.getString("id");
            String isProduto = params.getString("produtoOuCategoria");

            if(isProduto.equalsIgnoreCase("categoria"))
            {
                getSupportActionBar().setTitle("");
               /* getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.WhiteTwo)));*/
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            }


            getDescricaoProduto(Long.valueOf(id));
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ProdutoActivity.this, R.style.DialogStyle);

                alertDialogBuilder
                        .setMessage("Produto reservado com sucesso")
                        .setCancelable(false)
                        .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                               // ProdutoActivity.this.finish();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public void getDescricaoProduto(long id) {
        ApiService api = new ApiService();
        Retrofit retrofit = api.getInstance();
        IApiService iApiService = retrofit.create(IApiService.class);
        Call<Produto> call = iApiService.listProdutosPorId(id);

        call.enqueue(new Callback<Produto>() {
            @Override
            public void onResponse(Call<Produto> call, Response<Produto> response) {

                String urlImagem = response.body().getUrlImagem();
                Glide.with(ProdutoActivity.this).load(urlImagem).into(imv_produto);

                txtDescricao.setText(Html.fromHtml(response.body().getDescricao()));
                txt_nm_produto.setText(response.body().getNome());
                txtPrecoDe.setPaintFlags(txtPrecoDe.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                txtPrecoDe.setText("De: " + String.valueOf(response.body().getPrecoDe()));
                txtPrecoPor.setText("Por: " + String.valueOf(response.body().getPrecoPor()));
            }

            @Override
            public void onFailure(Call<Produto> call, Throwable t) {

            }
        });
    }
}
