package SQLite_repos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ex.alvaro.pronunciatel.actCuentos;
import com.ex.alvaro.pronunciatel.actPreguntasCuentos;

import java.util.ArrayList;

import Clases.Cuento;
import Clases.PreguntasCuento;
import Coneccion.Conexion;
import Repos.repoCuentos;

/**
 * Created by Alvaro on 21/05/2015.
 */
public class sqliteCuentos implements repoCuentos {
    @Override
    public Cuento cargarCuento() {
        Cuento cuento=new Cuento("");
        Conexion conex=Conexion.getInstance(actCuentos.con);
        SQLiteDatabase db= conex.getWritableDatabase();

        String consulta="select id, cuento from cuentos";
        Cursor fila=db.rawQuery(consulta,null);

        //conseguir la cantidad de preguntas
        //int numero=obtenerAleatorio();
        int numero=obtenerAleatorio();

        if (fila.moveToFirst()){
            for (int a=0;a<numero;a++){
                fila.moveToNext();
            }
            cuento.setId(fila.getInt(0));
            Log.v("ID: ", fila.getInt(0)+" -.....");

            cuento.setCuento(fila.getString(1));
            Log.v("CUENTO: ", fila.getString(1)+" -.....");
        }

        return cuento;
    }

    @Override
    public ArrayList<PreguntasCuento> cargarPreguntas(int id) {
        ArrayList <PreguntasCuento> preguntas=new ArrayList<>();
        Conexion conex=Conexion.getInstance(actPreguntasCuentos.con);
        SQLiteDatabase db= conex.getWritableDatabase();

        String consulta="select pregunta, respuesta1, respuesta2, respuesta3 from preguntascuento where idcuento='"+id+"'";
        Cursor fila=db.rawQuery(consulta,null);

        //conseguir la cantidad de preguntas
        //int numero=obtenerAleatorio();
        int numero=0;

        if (fila.moveToFirst()){
            do {
                PreguntasCuento preg = new PreguntasCuento(id);
                preg.setPregunta(fila.getString(0));
                Log.v("DENTRO DEL WHILE", fila.getString(0));
                preg.setRespuesta(fila.getString(1));
                preg.setRespuesta2(fila.getString(2));
                preg.setRespuesta3(fila.getString(3));
                preguntas.add(preg);
            }while (fila.moveToNext());
        }

        return preguntas;
    }

    private int obtenerAleatorio() {
        return (int) (Math.random()*Conexion.cuentos.size());
    }
}
