package com.example.listingsmovies.login.user;

import android.content.Context;
import android.content.SharedPreferences;

public class Usuario {
    String logged;
    Context context;
    SharedPreferences sharedPreferences;

    public Usuario(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("infoUsuario", Context.MODE_PRIVATE);
    }

    public String getLogged() {
        logged = sharedPreferences.getString("logged", "");
        return logged;
    }

    public void setLogged(String logged) {
        this.logged = logged;
        sharedPreferences.edit().putString("logged", logged).commit();
    }

    public void removeUser(){
        sharedPreferences.edit().clear().commit();
    }
}
