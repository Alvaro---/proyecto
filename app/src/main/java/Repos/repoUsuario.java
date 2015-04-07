package Repos;

import android.content.Context;

import java.util.ArrayList;

import Clases.Usuario;

/**
 * Created by Alvaro on 04/03/2015.
 */
public interface repoUsuario {
    public abstract String cargarUsuario ();

    public boolean guardarUsuario(Usuario u);

    ArrayList<String> cargarTodos();
}
