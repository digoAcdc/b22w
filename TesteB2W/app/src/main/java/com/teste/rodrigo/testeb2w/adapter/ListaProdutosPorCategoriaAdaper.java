package com.teste.rodrigo.testeb2w.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teste.rodrigo.testeb2w.Model.Produto;
import com.teste.rodrigo.testeb2w.R;

import java.util.List;

/**
 * Created by not on 10/02/2017.
 */

public class ListaProdutosPorCategoriaAdaper extends RecyclerView.Adapter<ListaProdutosPorCategoriaAdaper.ProdutosPorCategoriaViewHolder> {


    private Context context;
    private List<Produto> lstProdutos;
    private final ProdutoOnClickListener onClickListener;



    public interface ProdutoOnClickListener {
        public void OnClickitemLista(View view, int index);
    }

    public ListaProdutosPorCategoriaAdaper(Context context, List<Produto> lstProduto, ProdutoOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.context = context;
        this.lstProdutos = lstProduto;

    }

    @Override
    public ProdutosPorCategoriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_lista_produtos_categoria_item, parent, false);
        ListaProdutosPorCategoriaAdaper.ProdutosPorCategoriaViewHolder holder = new ListaProdutosPorCategoriaAdaper.ProdutosPorCategoriaViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ProdutosPorCategoriaViewHolder holder, final int position) {
        Produto p = lstProdutos.get(position);

        holder.descProduto.setText(p.getNome());
        holder.produtoDe.setPaintFlags(holder.produtoDe.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.produtoDe.setText("De: " + String.valueOf(p.getPrecoDe()));
        holder.produtoPor.setText("Por: " + String.valueOf(p.getPrecoPor()));
        holder.txtIdProduto.setText(String.valueOf(p.getId()));
        Glide.with(context).load(p.getUrlImagem()).into(holder.imgProdMaisVend);

        if (onClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.OnClickitemLista(holder.view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return lstProdutos.size();
    }



    public static class ProdutosPorCategoriaViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProdMaisVend;
        ImageView imgDetalhes;
        TextView descProduto;
        TextView produtoDe;
        TextView produtoPor;
        TextView txtIdProduto;

        private View view;

        public ProdutosPorCategoriaViewHolder(View view) {
            super(view);
            this.view = view;
            this.descProduto = (TextView) view.findViewById(R.id.descProduto);
            this.produtoDe = (TextView) view.findViewById(R.id.produtoDe);
            this.produtoPor = (TextView) view.findViewById(R.id.produtoPor);
            this.txtIdProduto = (TextView) view.findViewById(R.id.txtIdProduto);
            this.imgProdMaisVend = (ImageView) view.findViewById(R.id.imgProdMaisVend);
            this.imgDetalhes = (ImageView) view.findViewById(R.id.imgDetalhes);

        }
    }
}

