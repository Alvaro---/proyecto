package SQLite_repos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ex.alvaro.pronunciatel.actImagenes;

import Clases.ActImagenes;
import Coneccion.Conexion;
import Repos.repoActImagenes;

/**
 * Created by Alvaro on 09/04/2015.
 */
public class sqliteActImagenes implements repoActImagenes {


    private String select="select Palabra, imagen, descripcion, palabra2, palabra3, palabra4 from actimagen";
    @Override
    public void cargarImagenAleatoria() {
        ActImagenes act=ActImagenes.getInstanciaActImagenes(actImagenes.con);

        Conexion conex=Conexion.getInstance(actImagenes.con);
        SQLiteDatabase db= conex.getWritableDatabase();

        String consulta=select;
        Cursor fila=db.rawQuery(consulta,null);

        //CORREGIR EL NUMERO CON EL TOTAL DE DATOS EN SQLITE
        int numero=(int) (Math.random()*32);

        if (fila.moveToFirst()){
            for (int a=0;a<numero;a++){
                fila.moveToNext();
            }
            act.setImagen(fila.getString(1));
            act.setPalabraObjetivo(fila.getString(0));
            act.setDetallePalabra(fila.getString(2));
            act.setPalabraObjetivo2(fila.getString(3));
            act.setPalabraObjetivo3(fila.getString(4));
            act.setPalabraObjetivo4(fila.getString(5));
        }
    }
}
