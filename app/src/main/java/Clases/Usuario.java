package Clases;

import android.content.Context;

import java.util.ArrayList;

import Repos.repoUsuario;
import SQLite_repos.sqliteUsuario;

/**
 * Created by Alvaro on 04/03/2015.
 */
public class Usuario {
    String nombre;
    int id;

    repoUsuario repository=new sqliteUsuario();

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void cargarUsuario (){
        this.nombre=repository.cargarUsuario();
    }

    public boolean guardarNuevoUsuario() {
        return repository.guardarUsuario(this);
    }

    public ArrayList<String> cargarTodosNombre() {
        return repository.cargarTodos();
    }
}
