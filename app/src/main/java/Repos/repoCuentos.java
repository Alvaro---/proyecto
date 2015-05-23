package Repos;

import java.util.ArrayList;

import Clases.Cuento;
import Clases.PreguntasCuento;

/**
 * Created by Alvaro on 21/05/2015.
 */
public interface repoCuentos {
    Cuento cargarCuento();


    ArrayList<PreguntasCuento> cargarPreguntas(int id);
}
