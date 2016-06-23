package com.labo5.fvillella.procesos;

import android.util.Log;
import android.util.Xml;

import com.labo5.fvillella.entidades.Noticia;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vicente on 12/06/2016.
 */
public class RSSParser {
    public static List<Noticia> parsearRSS(String rss){
        Log.d("entry",rss);
        List<Noticia> noticias = new ArrayList<Noticia>();
        Noticia noticia = null;
        XmlPullParser parser = Xml.newPullParser();
        try {
            parser.setInput(new StringReader(rss));

            boolean esItem = false;

            int evento = parser.getEventType();
            while (evento != XmlPullParser.END_DOCUMENT) {
                if (evento == XmlPullParser.START_TAG) {
                    if (parser.getName().equalsIgnoreCase("item")) {
                        esItem = true;
                        noticia = new Noticia();
                    } else if (parser.getName().equalsIgnoreCase("title")) {
                        if (esItem){
                            noticia.setTitulo(parser.nextText());
                        }
                    } else if (parser.getName().equalsIgnoreCase("link")) {
                        if (esItem){
                            noticia.setLink(parser.nextText());
                        }
                    } else if (parser.getName().equalsIgnoreCase("description")) {
                        if (esItem){
                            noticia.setDescripcion(parser.nextText());
                        }
                    } else if (parser.getName().equalsIgnoreCase("enclosure")) {
                        if (esItem){
                            if(parser.getAttributeValue(null, "type").contains("image")) {
                                noticia.setImagenLink(parser.getAttributeValue(null, "url"));
                            }
                        }
                    } else if (parser.getName().equalsIgnoreCase("pubDate")) {
                        if (esItem){
                            DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm zzz");
                            DateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");

                            Date date = formatter.parse(parser.nextText());
                            String fechafinal = formatter2.format(date);

                            noticia.setFecha(fechafinal);
                        }
                    }
                } else if (evento == XmlPullParser.END_TAG && parser.getName().equalsIgnoreCase("item")) {
                     esItem = false;
                     noticias.add(noticia);
                }
                evento = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("Lista: ",noticias.toString());
        return noticias;
    }
}
