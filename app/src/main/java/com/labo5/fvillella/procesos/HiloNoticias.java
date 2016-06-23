package com.labo5.fvillella.procesos;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.labo5.fvillella.entidades.Noticia;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Vicente on 11/06/2016.
 */
public class HiloNoticias implements Runnable {

    Handler handler;
    HttpManager httpManager;

    public HiloNoticias(String url, Handler handler) {
        this.handler = handler;
        this.httpManager = new HttpManager(url);
    }

    @Override
    public void run() {
        Message mes;

        try {
            String rss = httpManager.getStrDataByGET();

            List<Noticia> noticias = RSSParser.parsearRSS(rss);
            Log.d("Cantidad noticias: ", String.valueOf(noticias.size()));

            /* Enviamos mensaje con las noticias parseadas */
            mes = new Message();
            mes.obj = noticias;
            mes.arg1 = 1;
            handler.sendMessage(mes);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
