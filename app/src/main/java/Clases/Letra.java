package Clases;

/**
 * Created by Alvaro on 11/05/2015.
 */
public class Letra {
    String letra;
    String ejemplo;
    String imagenLetra;
    String imagenEjemplo;

    public Letra(String letra, String ejemplo, String imagenLetra, String imagenEjemplo) {
        this.letra = letra;
        this.ejemplo = ejemplo;
        this.imagenLetra = imagenLetra;
        this.imagenEjemplo = imagenEjemplo;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getEjemplo() {
        return ejemplo;
    }

    public void setEjemplo(String ejemplo) {
        this.ejemplo = ejemplo;
    }

    public String getImagenLetra() {
        return imagenLetra;
    }

    public void setImagenLetra(String imagenLetra) {
        this.imagenLetra = imagenLetra;
    }

    public String getImagenEjemplo() {
        return imagenEjemplo;
    }

    public void setImagenEjemplo(String imagenEjemplo) {
        this.imagenEjemplo = imagenEjemplo;
    }
}
