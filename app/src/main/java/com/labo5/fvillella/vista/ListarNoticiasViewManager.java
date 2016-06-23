package com.labo5.fvillella.vista;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import com.labo5.fvillella.activities.AlertaDialogo;
import com.labo5.fvillella.activities.ListarNoticias;
import com.labo5.fvillella.activities.R;
import com.labo5.fvillella.controlador.ListarNoticiasControlador;

/**
 * Created by Vicente on 22/06/2016.
 */
public class ListarNoticiasViewManager {
    ListarNoticias listarNoticias;
    Button btnLeer;
    EditText etUrl;
    RecyclerView recyclerView;

    public ListarNoticiasViewManager(ListarNoticias listarNoticias){
        this.listarNoticias = listarNoticias;
        this.btnLeer = (Button)this.listarNoticias.findViewById(R.id.btnLeer);
        this.etUrl = (EditText)this.listarNoticias.findViewById(R.id.etUrl);
        this.recyclerView = (RecyclerView) this.listarNoticias.findViewById(R.id.rv);
    }

    public EditText getEtUrl(){
        return this.etUrl;
    }

    public RecyclerView getRecyclerView(){
        return this.recyclerView;
    }

    public Handler getHandler(){
        return this.listarNoticias.getHandler();
    }

    public void setListenerLeerRSS(ListarNoticiasControlador controlador){
        this.btnLeer.setOnClickListener(controlador);
    }

    public void mostrarAlerta(){
        AlertaDialogo dialog = new AlertaDialogo();
        dialog.show(this.listarNoticias.getSupportFragmentManager(),"alerta");
    }
}
