package com.example.listingsmovies.ui.blog.modelcomment;

public class Comentario {
    private String userId;
    private String comentario;
    private String fechaComentario;
    private String cover;
    private String nombreUsuario;
    private String idUsuario;

    public Comentario() {}

    public Comentario(String userId, String comentario, String fechaComentario, String cover, String nombreUsuario, String idUsuario) {
        this.userId = userId;
        this.comentario = comentario;
        this.fechaComentario = fechaComentario;
        this.cover = cover;
        this.nombreUsuario = nombreUsuario;
        this.idUsuario = idUsuario;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(String fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
}
