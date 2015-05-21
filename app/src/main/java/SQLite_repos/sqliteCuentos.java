package SQLite_repos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ex.alvaro.pronunciatel.actCuentos;

import Clases.Cuento;
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
        int numero=0;

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

    private int obtenerAleatorio() {
        return (int) (Math.random()*Conexion.cuentos.size());
    }
}
