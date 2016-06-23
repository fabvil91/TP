package com.labo5.fvillella.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.labo5.fvillella.activities.ListarNoticias;
import com.labo5.fvillella.activities.R;
import com.labo5.fvillella.entidades.Noticia;
import com.labo5.fvillella.listeners.ClickNoticia;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Vicente on 11/06/2016.
 */
public class AdapterNoticias extends RecyclerView.Adapter<ViewHolderNoticias> {
    List<Noticia> noticias;
    ClickNoticia clickNoticia;
    Context contexto;

    public AdapterNoticias(List<Noticia> noticias, ListarNoticias listarNoticias) {
        this.noticias = noticias;
        this.clickNoticia = listarNoticias;
        this.contexto = listarNoticias;
    }

    @Override
    public ViewHolderNoticias onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewNoticiaItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.noticia_item, parent, false);
        ViewHolderNoticias vH = new ViewHolderNoticias(viewNoticiaItem, this.clickNoticia);
        return vH;
    }

    @Override
    public void onBindViewHolder(ViewHolderNoticias holder, int position) {
        Noticia p = noticias.get(position);

        holder.tvFecha.setText(p.getFecha());
        holder.tvDescripcion.setText(p.getDescripcion());
        holder.tvTitulo.setText(p.getTitulo());
        if(p.getImagenLink() != null){
            Picasso.with(contexto).load(p.getImagenLink()).placeholder(R.drawable.rss).resize(300, 300).into(holder.imgNoticia);
        }
        holder.setPosicion(position);
    }

    @Override
    public int getItemCount() {
        return this.noticias.size();
    }
}
