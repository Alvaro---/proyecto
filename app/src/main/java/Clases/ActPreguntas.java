package Clases;

import android.content.Context;

import Repos.repoActPreguntas;
import SQLite_repos.sqliteActPreguntas;

/**
 * Created by Alvaro on 13/04/2015.
 */
public class ActPreguntas extends Actividad{

    String pregunta="";
    int idPregunta;
    //SINGLETON
    private static ActPreguntas instancioActPreguntas=null;
    //REPOSITORY
    repoActPreguntas repository=new sqliteActPreguntas();

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

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    @Override
    public void buscarAleatorio() {
        super.buscarAleatorio();
        repository.cargarPregunta();
    }

}
