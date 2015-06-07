package Repos;

import Clases.cadenaFonematica;

/**
 * Created by HP on 01/06/2015.
 */
public interface repoCadenaFonematica {

    boolean tieneInicial(cadenaFonematica cad);

    boolean tieneMedia(cadenaFonematica cadenaFonematica);

    boolean tieneFinal(cadenaFonematica cadenaFonematica);

    boolean tieneInversa(cadenaFonematica cadenaFonematica);

    cadenaFonematica cargarImagen(cadenaFonematica cadenaFonematica);
}
