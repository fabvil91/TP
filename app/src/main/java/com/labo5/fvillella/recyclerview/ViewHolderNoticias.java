package com.labo5.fvillella.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.labo5.fvillella.activities.R;
import com.labo5.fvillella.listeners.ClickNoticia;

/**
 * Created by Vicente on 11/06/2016.
 */
public class ViewHolderNoticias extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView tvFecha;
    TextView tvTitulo;
    TextView tvDescripcion;
    ImageView imgNoticia;
    ClickNoticia clickNoticia;
    int posicion;

    public ViewHolderNoticias(View v, ClickNoticia clickNoticia){
        super(v);
        this.tvFecha = (TextView) v.findViewById(R.id.tvFecha);
        this.tvTitulo = (TextView) v.findViewById(R.id.tvTitulo);
        this.tvDescripcion = (TextView) v.findViewById(R.id.tvDescripcion);
        this.imgNoticia = (ImageView) v.findViewById(R.id.imgNoticia);
        this.clickNoticia = clickNoticia;
        v.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.clickNoticia.hacerClick(this.posicion);
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}
