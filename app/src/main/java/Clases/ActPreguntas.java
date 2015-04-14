package Clases;

import android.content.Context;

import Repos.repoActPreguntas;
import SQLite_repos.sqliteActPreguntas;

/**
 * Created by Alvaro on 13/04/2015.
 */
public class ActPreguntas extends Actividad{

    String pregunta="";

    repoActPreguntas repository=new sqliteActPreguntas();

    //SINGLETON
    private static ActPreguntas instancioActPreguntas=null;

    public static ActPreguntas getInstanciaActPreguntas(Context con){
        if (instancioActPreguntas==null){
            instancioActPreguntas=new ActPreguntas();
        }
        return instancioActPreguntas;
    }

    private ActPreguntas (){
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public void buscarAleatorio() {
        super.buscarAleatorio();
        repository.cargarPregunta();
    }

}
