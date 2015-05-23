package Clases;

/**
 * Created by Alvaro on 21/05/2015.
 */
public class PreguntasCuento {

    int idCuento;
    String pregunta;
    String respuesta;
    String respuesta2;
    String respuesta3;

    public PreguntasCuento(int idCuento) {
        this.idCuento = idCuento;
    }

    public int getIdCuento() {
        return idCuento;
    }

    public void setIdCuento(int idCuento) {
        this.idCuento = idCuento;
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

    public String getRespuesta2() {
        return respuesta2;
    }

    public void setRespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }

    public String getRespuesta3() {
        return respuesta3;
    }

    public void setRespuesta3(String respuesta3) {
        this.respuesta3 = respuesta3;
    }

}
