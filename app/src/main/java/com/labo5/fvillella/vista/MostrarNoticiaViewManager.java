package com.labo5.fvillella.vista;

import android.webkit.WebView;

import com.labo5.fvillella.activities.MostrarNoticia;
import com.labo5.fvillella.activities.R;

/**
 * Created by Vicente on 22/06/2016.
 */
public class MostrarNoticiaViewManager {
    MostrarNoticia mostrarNoticia;
    WebView webView;

    public MostrarNoticiaViewManager(MostrarNoticia mostrarNoticia){
        this.mostrarNoticia = mostrarNoticia;
        this.webView = (WebView)this.mostrarNoticia.findViewById(R.id.webView1);
    }

    public WebView getWebView(){
        return this.webView;
    }
}
