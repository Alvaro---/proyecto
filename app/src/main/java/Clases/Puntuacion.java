package Clases;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Repos.repoPuntuacion;
import SQLite_repos.sqlitePuntuacion;

/**
 * Created by Alvaro on 13/04/2015.
 */
public class Puntuacion {
    int valor;
    repoPuntuacion repository=new sqlitePuntuacion();

    String fecha;
    String hora;
    float puntuacion;
    int idUsuario;
    String palabra;
    String actividad;

    public Puntuacion() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public float getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }


    //Constructor y metodos para puntuar

    public Puntuacion(int a) {
        valor=a;
    }

    public float unEjercicio(int rep, String actividad, int puntosReconocimiento, int idPalabra, String palabraPronunciada){
        float val=100-(valor*10)-puntosReconocimiento;
        float puntuacion=val-rep;
        repository.guardarPuntuacion(puntuacion,actividad,idPalabra,palabraPronunciada, Hora(), Fecha());
        return puntuacion;
    }

    public ArrayList<Puntuacion> cargarResultados() {
        return repository.cargarPuntuaciones(this.getIdUsuario());
    }


    private String Hora() {
        long ahora = System.currentTimeMillis();
        Calendar calendario = Calendar.getInstance();
        calendario.setTimeInMillis(ahora);
        int hora = calendario.get(Calendar.HOUR_OF_DAY);
        int minuto = calendario.get(Calendar.MINUTE);
        int seg=calendario.get(Calendar.SECOND);
        return hora+":"+minuto+":"+seg+" ";
    }

    private String Fecha() {
        Calendar cal = new GregorianCalendar();
        Date date = cal.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public float unaPregunta(int rep, String actividad, int idPregunta, String palabraPronunciad) {
        float val=100-(valor*10);
        float puntuacion=val-rep;
        repository.guardarPuntuacionPregunta(puntuacion,actividad,idPregunta,palabraPronunciad, Hora(), Fecha());
        return puntuacion;
    }
}
