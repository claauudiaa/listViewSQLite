package com.example.listviewsqlite;

public class Item {
    public String titulo;
    public String datos;

    public Item(String titulo, String datos) {
        this.titulo = titulo;
        this.datos = datos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }
}
