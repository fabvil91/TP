package com.labo5.fvillella.entidades;

import android.graphics.Bitmap;

import java.util.Date;

/**
 * Created by Vicente on 11/06/2016.
 */
public class Noticia {
    private String titulo;
    private String descripcion;
    private String link;
    private String fecha;
    private String imagenLink;
    private Bitmap imagen;

    public Noticia(String titulo, String descripcion, String link, String fecha, String imagenLink) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.link = link;
        this.fecha = fecha;
        this.imagenLink = imagenLink;
    }

    public Noticia(){

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getImagenLink() {
        return imagenLink;
    }

    public void setImagenLink(String imagenLink) {
        this.imagenLink = imagenLink;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Noticia{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", link='" + link + '\'' +
                ", fecha=" + fecha +
                ", imagenLink='" + imagenLink + '\'' +
                ", imagen=" + imagen +
                '}';
    }
}
