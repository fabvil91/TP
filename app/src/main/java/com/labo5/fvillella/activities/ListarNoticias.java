package com.labo5.fvillella.activities;

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

import com.labo5.fvillella.entidades.Noticia;
import com.labo5.fvillella.listeners.ClickNoticia;
import com.labo5.fvillella.procesos.HiloNoticias;
import com.labo5.fvillella.recyclerview.AdapterNoticias;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListarNoticias extends AppCompatActivity implements ClickNoticia, View.OnClickListener,Handler.Callback{
    List<Noticia> noticias = new ArrayList<Noticia>();
    AdapterNoticias adapterNoticias;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_noticias);

        this.findViewById(R.id.btnLeer).setOnClickListener(this);

        adapterNoticias = new AdapterNoticias(noticias, this,this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapterNoticias);
        recyclerView.setLayoutManager(layoutManager);

        handler = new Handler(this);
    }

    @Override
    public void hacerClick(int position) {
        Log.d("Posicion:" , String.valueOf(position));
    }

    @Override
    public void onClick(View v) {
        EditText txtUrl = (EditText)this.findViewById(R.id.etUrl);
        if(txtUrl.getText() != null && txtUrl.getText().toString() != null && !txtUrl.getText().toString().isEmpty()){
            HiloNoticias hiloNoticias = new HiloNoticias("http://www.nasa.gov/rss/dyn/lg_image_of_the_day.rss", handler, false,0);
            Thread tHilo = new Thread(hiloNoticias);
            tHilo.start();
        }else{
            Log.d("Error","Escriba algo");
        }

    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.arg1 == 1) {
            noticias.clear();
            noticias.addAll((List<Noticia>) msg.obj);
            Log.d("HandlerMessaggee", noticias.size() + "");
            adapterNoticias.notifyDataSetChanged();
        }  else if (msg.arg1 == 2) {
            byte[] array = (byte[])msg.obj;
            if(array != null && array.length > 0){
                Bitmap bmp = BitmapFactory.decodeByteArray(array, 0, array.length);
                (noticias.get(msg.arg2)).setImagen(bmp);
                adapterNoticias.notifyDataSetChanged();
            }
    }
        return false;
    }
}
