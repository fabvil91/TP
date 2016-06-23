package com.labo5.fvillella.controlador;

import android.view.View;
import android.widget.EditText;

import com.labo5.fvillella.procesos.HiloNoticias;
import com.labo5.fvillella.vista.ListarNoticiasViewManager;

/**
 * Created by Vicente on 22/06/2016.
 */
public class ListarNoticiasControlador implements  View.OnClickListener{
    ListarNoticiasViewManager listarNoticiasViewManager;

    public ListarNoticiasControlador(ListarNoticiasViewManager listarNoticiasViewManager){
        this.listarNoticiasViewManager = listarNoticiasViewManager;
    }

    @Override
    public void onClick(View v) {
        EditText etUrl = this.listarNoticiasViewManager.getEtUrl();
        if(etUrl.getText() != null && etUrl.getText().toString() != null && !etUrl.getText().toString().isEmpty()){
            /* Lanzamos hilo que busca en Internet el RSS, lo parsea y luego lo envia al proceso principal */
            HiloNoticias hiloNoticias = new HiloNoticias("http://www.nasa.gov/rss/dyn/lg_image_of_the_day.rss", this.listarNoticiasViewManager.getHandler());
            Thread tHilo = new Thread(hiloNoticias);
            tHilo.start();
        }else{
            this.listarNoticiasViewManager.mostrarAlerta();
        }
    }
}
