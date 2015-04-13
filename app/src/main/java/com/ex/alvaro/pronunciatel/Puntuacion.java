package com.ex.alvaro.pronunciatel;

/**
 * Created by Alvaro on 13/04/2015.
 */
public class Puntuacion {
    int valor;
    public Puntuacion(int a) {
        valor=a;
    }

    public float unEjercicio(){
        float val=100-(valor*10);
        return val;
    }
}
