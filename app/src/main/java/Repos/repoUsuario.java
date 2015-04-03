package Repos;

import android.content.Context;

import Clases.Usuario;

/**
 * Created by Alvaro on 04/03/2015.
 */
public interface repoUsuario {
    public abstract String cargarUsuario ();

    public boolean guardarUsuario(Usuario u);
}
