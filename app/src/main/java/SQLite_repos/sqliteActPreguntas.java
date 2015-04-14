package SQLite_repos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ex.alvaro.pronunciatel.actImagenes;
import com.ex.alvaro.pronunciatel.actPreguntas;

import Clases.ActPreguntas;
import Coneccion.Conexion;
import Repos.repoActPreguntas;

/**
 * Created by Alvaro on 13/04/2015.
 */
public class sqliteActPreguntas implements repoActPreguntas {

    String select ="select pregunta,palabra,palabra2,imagen from actpreguntas";

    @Override
    public void cargarPregunta() {
        ActPreguntas pregunta=ActPreguntas.getInstanciaActPreguntas(actPreguntas.con);
        Conexion conex=Conexion.getInstance(actImagenes.con);
        SQLiteDatabase db= conex.getWritableDatabase();

        String consulta=select;
        Cursor fila=db.rawQuery(consulta,null);

        //conseguir la cantidad de preguntas
        int numero=(int) (Math.random()*16);

        if (fila.moveToFirst()){
            for (int a=0;a<numero;a++){
                fila.moveToNext();
            }
            pregunta.setPregunta(fila.getString(0));
            pregunta.setPalabraObjetivo(fila.getString(1));
            pregunta.setPalabraObjetivo2(fila.getString(2));
            pregunta.setImagen(fila.getString(3));

            Log.v("PREGUNTA: ",fila.getString(0));
        }



    }
}
