package com.example.listingsmovies.ui.blog.addcomment.modeladdcomment;

public class AddComment {
    String id;
    String titulo;

    public AddComment() {}

    public AddComment(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
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
}
