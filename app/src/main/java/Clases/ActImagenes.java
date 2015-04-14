package Clases;

import android.content.Context;

import Repos.repoActImagenes;
import SQLite_repos.sqliteActImagenes;

/**
 * Created by Alvaro on 09/04/2015.
 */
public class ActImagenes extends Actividad{

    private static ActImagenes instanciaActImagenes=null;

    public static ActImagenes getInstanciaActImagenes(Context con){
        if (instanciaActImagenes==null){
            instanciaActImagenes=new ActImagenes();
        }
        return instanciaActImagenes;
    }

    String detallePalabra;

    repoActImagenes repository =new sqliteActImagenes();

    private ActImagenes() {
    }

    public String getDetallePalabra() {
        return detallePalabra;
    }

    public void setDetallePalabra(String detallePalabra) {
        this.detallePalabra = detallePalabra;
    }

    @Override
    public void buscarAleatorio() {
        super.buscarAleatorio();
        repository.cargarAleatoria();
    }
}
