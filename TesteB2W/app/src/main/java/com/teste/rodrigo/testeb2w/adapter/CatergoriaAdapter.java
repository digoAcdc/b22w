package com.teste.rodrigo.testeb2w.adapter;



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.teste.rodrigo.testeb2w.Model.Categoria;
import com.teste.rodrigo.testeb2w.R;

import java.util.List;

/**
 * Created by not on 10/02/2017.
 */

public class CatergoriaAdapter extends RecyclerView.Adapter<CatergoriaAdapter.CategoriaViewHolder> {


    private Context context;
    private List<Categoria> lstCategoria;
    private final CategoriaOnClickListener onClickListener;

    public interface CategoriaOnClickListener {
        public void OnClickitemLista(View view, int index);
    }

    public CatergoriaAdapter(Context context, List<Categoria> lstCategoria, CategoriaOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.context = context;
        this.lstCategoria = lstCategoria;

    }


    @Override
    public CategoriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_categoria_item, parent, false);
        CategoriaViewHolder holder = new CategoriaViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final CategoriaViewHolder holder, final int position) {
        Categoria c = lstCategoria.get(position);

        holder.tvDescItemCategoria.setText(c.getDescricao());
        holder.tvIdDescItemCategoria.setText(String.valueOf(c.getId()));
        Glide.with(context).load(c.getUrlImagem()).into(holder.ivItemCategoria);


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
        return lstCategoria.size();
    }


    public static class CategoriaViewHolder extends RecyclerView.ViewHolder {
        ImageView ivItemCategoria;
        TextView tvIdDescItemCategoria;

        TextView tvDescItemCategoria;

        private View view;

        public CategoriaViewHolder(View view) {
            super(view);
            this.view = view;
            this.tvIdDescItemCategoria = (TextView) view.findViewById(R.id.tvIdDescItemCategoria);
            this.tvDescItemCategoria = (TextView) view.findViewById(R.id.tvDescItemCategoria);
            this.ivItemCategoria = (ImageView) view.findViewById(R.id.ivItemCategoria);

        }
    }
}
