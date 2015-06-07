package Clases;

import java.util.ArrayList;

import Repos.repoCadenaFonematica;
import SQLite_repos.sqliteCadenaFonematica;

/**
 * Created by HP on 01/06/2015.
 */
public class cadenaFonematica {
    String letra;
    String imagen;
    String palabra;
    boolean inicial;
    boolean medio;
    boolean fin;
    boolean inverso;
    String posicione;

    repoCadenaFonematica repository=new sqliteCadenaFonematica();

    public cadenaFonematica(String letra) {
        this.letra = letra;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }


    public String getPosicione() {
        return posicione;
    }

    public void setPosicione(String posicione) {
        this.posicione = posicione;
    }


    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public ArrayList<String> obtenerArrayPosiciones() {
        ArrayList<String> posiciones =new ArrayList<>();
        if (repository.tieneInicial(this))
            posiciones.add("inicial");
        if(repository.tieneMedia(this))
            posiciones.add("media");
        if (repository.tieneFinal(this))
            posiciones.add("final");
        if (repository.tieneInversa(this))
            posiciones.add(("inversa"));

        return posiciones;
    }

    public void cambiarImagenObjetivo() {
        cadenaFonematica a = repository.cargarImagen(this);
        setImagen(a.getImagen());
        setPalabra(a.getPalabra());
    }
}
