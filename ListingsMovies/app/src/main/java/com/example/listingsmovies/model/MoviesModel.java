package com.example.listingsmovies.model;

public class MoviesModel {

    private String id;
    private String titulo;
    private String actores;
    private String director;
    private String genero;
    private String imagen;
    private String year;


    public MoviesModel() {}

    public MoviesModel(String id, String titulo, String actores, String director, String genero, String imagen, String year) {
        this.id = id;
        this.titulo = titulo;
        this.actores = actores;
        this.director = director;
        this.genero = genero;
        this.imagen = imagen;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getActores() {
        return actores;
    }

    public void setActores(String actores) {
        this.actores = actores;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
