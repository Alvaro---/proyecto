package Clases;

import Repos.repoPuntuacion;
import SQLite_repos.sqlitePuntuacion;

/**
 * Created by Alvaro on 13/04/2015.
 */
public class Puntuacion {
    int valor;
    repoPuntuacion repository=new sqlitePuntuacion();

    public Puntuacion(int a) {
        valor=a;
    }

    public float unEjercicio(int rep, String actividad){
        float val=100-(valor*10);
        float puntuacion=val-rep;
        repository.guardarPuntuacion(puntuacion,actividad);
        return puntuacion;
    }
}
