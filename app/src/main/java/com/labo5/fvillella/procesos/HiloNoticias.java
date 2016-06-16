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
    boolean esImagen;
    int posicion;
    HttpManager httpManager;

    public HiloNoticias(String url, Handler handler, boolean esImagen,int posicion) {
        this.handler = handler;
        this.esImagen = esImagen;
        this.posicion = posicion;
        this.httpManager = new HttpManager(url);
    }

    @Override
    public void run() {
        Message mes;

        try {
            if(this.esImagen){
                try {
                    byte[] arrayImg = httpManager.getBytesDataByGET();

                    mes = new Message();
                    mes.arg1 = 2;
                    mes.arg2 = this.posicion;
                    mes.obj = arrayImg;

                    handler.sendMessage(mes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                String rss = httpManager.getStrDataByGET();
                Log.d("lista", rss.substring(0,50));
                //"<item> <title>Hubble Uncovers a Mysterious Hermit</title></item>"
                List<Noticia> noticias = RSSParser.parsearRSS(rss);
                Log.d("lista", String.valueOf(noticias.size()));

   /*             ExecutorService executorService = Executors.newFixedThreadPool(3);
                int i = 0;
                for (Noticia noticia: noticias) {
                    HiloNoticias hiloImagen = new HiloNoticias(noticias.get(0).getImagenLink(),this.handler,true,i);
                    i++;
                   // Thread hilo = new Thread(hiloImagen);
                   // hilo.start();
                    executorService.execute(hiloImagen);
                }
                executorService.shutdown();*/

                mes = new Message();
                mes.obj = noticias;
                mes.arg1 = 1;
                handler.sendMessage(mes);
            }
        }catch (Exception e){

        }
    }
}
