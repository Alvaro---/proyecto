package Clases;

import android.content.Context;

import Repos.repoUsuario;
import SQLite_repos.sqliteUsuario;

/**
 * Created by Alvaro on 04/03/2015.
 */
public class Usuario {
    String nombre;
    Context con;

    repoUsuario repository=new sqliteUsuario();

    public Usuario(String nombre, Context con) {
        this.nombre = nombre;
        this.con=con;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void cargarUsuario (){
        this.nombre=repository.cargarUsuario(con);
    }
}
