package com.teste.rodrigo.testeb2w;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.teste.rodrigo.testeb2w.Constanstes.Constants;
import com.teste.rodrigo.testeb2w.Model.Banner;
import com.teste.rodrigo.testeb2w.Model.Banners;
import com.teste.rodrigo.testeb2w.Model.Categorias;
import com.teste.rodrigo.testeb2w.Model.Produtos;
import com.teste.rodrigo.testeb2w.adapter.CatergoriaAdapter;
import com.teste.rodrigo.testeb2w.adapter.ProdutosMaisVendidosAdapter;
import com.teste.rodrigo.testeb2w.api.ApiService;
import com.teste.rodrigo.testeb2w.api.IApiService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.carouselView)
    CarouselView carouselView;
    @BindView(R.id.rvCategorias)
    RecyclerView rvCategorias;
    @BindView(R.id.rvMaisVendidos)
    RecyclerView rvMaisVendidos;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    TextView tv_lojinha_nav;

    List<Banner> lstBanners;
    IApiService iApiService = new ApiService().getInstance().create(IApiService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.mipmap.logo_navbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        applyFontForToolbarTitle();
       /* tv_lojinha_nav = (TextView) drawer.findViewById(R.id.tv_lojinha_nav);

        Typeface typeface = Typeface.createFromAsset(getAssets(), Constants.FONTE_PACIFICO_REGULAR);

        tv_lojinha_nav.setTypeface(typeface);*/

        getImages();
        getCategorias();
        getProdutosMaisVendidos();
    }

    private void applyFontForToolbarTitle(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        for(int i = 0; i < toolbar.getChildCount(); i++){
            View view = toolbar.getChildAt(i);
            if(view instanceof TextView){
                TextView tv = (TextView) view;
                Typeface titleFont = Typeface.createFromAsset(getAssets(), "fonts/Pacifico-Regular.ttf");
                if(tv.getText().equals(toolbar.getTitle())){
                    tv.setTypeface(titleFont);
                    break;
                }
            }
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Glide.with(MainActivity.this).load(lstBanners.get(position).getUrlImagem()).into(imageView);
        }
    };


    public void getProdutosMaisVendidos() {

        Call<Produtos> call = iApiService.listProdutosMaisVendidos();
        call.enqueue(new Callback<Produtos>() {
            @Override
            public void onResponse(Call<Produtos> call, Response<Produtos> response) {
                rvMaisVendidos.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                rvMaisVendidos.setHasFixedSize(true);
                rvMaisVendidos.setAdapter(new ProdutosMaisVendidosAdapter(MainActivity.this, response.body().getProdutos(), onClickItemProdMaisVendido()));

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                // for horizontal
                //linearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);
                // for vertical
                linearLayoutManager.setOrientation(LinearLayout.VERTICAL);

                rvMaisVendidos.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(Call<Produtos> call, Throwable t) {

            }
        });
    }


    public void getCategorias() {
        Call<Categorias> call = iApiService.listCategorias();
        call.enqueue(new Callback<Categorias>() {
            @Override
            public void onResponse(Call<Categorias> call, Response<Categorias> response) {
                rvCategorias.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                rvCategorias.setHasFixedSize(true);
                rvCategorias.setAdapter(new CatergoriaAdapter(MainActivity.this, response.body().getCategorias(), onClickItemCategoria()));

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                // for horizontal
                linearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);
                // for vertical
                // linearLayoutManager.setOrientation(LinearLayout.VERTICAL);

                rvCategorias.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(Call<Categorias> call, Throwable t) {

            }
        });
    }

    private CatergoriaAdapter.CategoriaOnClickListener onClickItemCategoria() {
        return new CatergoriaAdapter.CategoriaOnClickListener() {
            @Override
            public void OnClickitemLista(View view, int index) {
                TextView tvId = (TextView) view.findViewById(R.id.tvIdDescItemCategoria);
                TextView tvDescItemCategoria = (TextView) view.findViewById(R.id.tvDescItemCategoria);

                Intent i = new Intent(MainActivity.this, ListaProdutosCategoriaActivity.class);
                i.putExtra("id", tvId.getText());
                i.putExtra("descCategoria", tvDescItemCategoria.getText());
                startActivity(i);
            }
        };
    }

    private ProdutosMaisVendidosAdapter.ProdutoOnClickListener onClickItemProdMaisVendido() {
        return new ProdutosMaisVendidosAdapter.ProdutoOnClickListener() {
            @Override
            public void OnClickitemLista(View view, int index) {
                TextView tvId = (TextView) view.findViewById(R.id.txtIdProduto);

                Intent i = new Intent(MainActivity.this, ProdutoActivity.class);
                i.putExtra("id", tvId.getText());
                i.putExtra("produtoOuCategoria", "produto");
                startActivity(i);

            }
        };
    }

    public void getImages() {
        Call<Banners> call = iApiService.listBanners();
        call.enqueue(new Callback<Banners>() {
            @Override
            public void onResponse(Call<Banners> call, Response<Banners> response) {
                lstBanners = response.body().getBanners();

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        carouselView.setImageListener(imageListener);
                        carouselView.setPageCount(lstBanners.size());
                    }
                });
            }

            @Override
            public void onFailure(Call<Banners> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Falha ao carregar os banners", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.mn_home) {
            // Handle the camera action
        } else if (id == R.id.mn_sobre) {
            Intent i = new Intent(this, SobreActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
