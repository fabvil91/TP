package com.labo5.fvillella.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.labo5.fvillella.controlador.ListarNoticiasControlador;
import com.labo5.fvillella.entidades.Noticia;
import com.labo5.fvillella.listeners.ClickNoticia;
import com.labo5.fvillella.procesos.HiloNoticias;
import com.labo5.fvillella.recyclerview.AdapterNoticias;
import com.labo5.fvillella.vista.ListarNoticiasViewManager;

import java.util.ArrayList;
import java.util.List;

public class ListarNoticias extends AppCompatActivity implements ClickNoticia,Handler.Callback{
    List<Noticia> noticias = new ArrayList<Noticia>();
    AdapterNoticias adapterNoticias;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_noticias);

        ListarNoticiasViewManager listarNoticiasViewManager = new ListarNoticiasViewManager(this);
        ListarNoticiasControlador listarNoticiasControlador = new ListarNoticiasControlador(listarNoticiasViewManager);

        listarNoticiasViewManager.setListenerLeerRSS(listarNoticiasControlador);

        adapterNoticias = new AdapterNoticias(noticias, this);

        RecyclerView recyclerView = listarNoticiasViewManager.getRecyclerView();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapterNoticias);
        recyclerView.setLayoutManager(layoutManager);

        handler = new Handler(this);
    }

    @Override
    public void hacerClick(int position) {
        Log.d("Posicion:" , String.valueOf(position));

        /* Lanzamos la activity MostrarNoticia para navegar por el item */
        Intent i = new Intent(this,MostrarNoticia.class);
        i.putExtra("url",this.noticias.get(position).getLink());

        startActivity(i);
    }

    @Override
    public boolean handleMessage(Message msg) {
        /* Refrescamos el Adapter con las noticias parseadas */
        if (msg.arg1 == 1) {
            noticias.clear();
            noticias.addAll((List<Noticia>) msg.obj);
            Log.d("HandlerMessage", noticias.size() + "");
            adapterNoticias.notifyDataSetChanged();
        }
        return false;
    }

    public Handler getHandler(){
        return this.handler;
    }
}
