package Clases;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import Repos.repoCuentos;
import SQLite_repos.sqliteCuentos;

/**
 * Created by Alvaro on 21/05/2015.
 */
public class Cuento {

    int id;
    ArrayList <PreguntasCuento> preguntas=new ArrayList<>();
    String cuento;

    repoCuentos repository=new sqliteCuentos();

    public Cuento(String cuento) {
        this.cuento = cuento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<PreguntasCuento> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(ArrayList<PreguntasCuento> preguntas) {
        this.preguntas = preguntas;
    }

    public String getCuento() {
        return cuento;
    }

    public void setCuento(String cuento) {
        this.cuento = cuento;
    }

    public void cargarCuento() {
        Cuento a=repository.cargarCuento();
        this.setCuento(a.getCuento());
        this.setId(a.getId());
        Log.v("cuento desde clase", this.getCuento());
    }

    public void cargarPreguntas() {
        try{
            preguntas.clear();
        }catch(Exception e){

        }
        setPreguntas(repository.cargarPreguntas(this.id));
    }
}
