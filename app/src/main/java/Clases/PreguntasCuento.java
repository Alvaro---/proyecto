package Clases;

/**
 * Created by Alvaro on 21/05/2015.
 */
public class PreguntasCuento {

    String pregunta;
    String respuesta;

    public PreguntasCuento(String pregunta, String respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
