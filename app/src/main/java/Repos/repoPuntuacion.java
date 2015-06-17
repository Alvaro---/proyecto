package Repos;

import java.util.ArrayList;

import Clases.Puntuacion;

/**
 * Created by Alvaro on 05/05/2015.
 */
public interface repoPuntuacion {
    void guardarPuntuacion(float puntuacion, String actividad, int idPalabra, String palabraPronunciada, String hora, String fecha);

    ArrayList<Puntuacion> cargarPuntuaciones(int idUsuario);

    void guardarPuntuacionPregunta(float puntuacion, String actividad, int idPregunta, String palabraPronunciad, String hora, String fecha);

    void actualizaPuntosCadenaFonematica(float punt, String letraActual, String posicion);

    void guardarPuntosCuento(int puntosCorreccion, int idCuento, String palabraPronunciad, String fecha, String hora);
}
