package SQLite_repos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ex.alvaro.pronunciatel.menuPrincipal;

import Coneccion.Conexion;
import Repos.repoUsuario;

/**
 * Created by Alvaro on 04/03/2015.
 */
public class sqliteUsuario implements repoUsuario {
    @Override
    public String cargarUsuario() {

        //Obtiene la instancia de la conexcion, llamando al conexto del menu principal
        Conexion conex=Conexion.getInstance(menuPrincipal.con);

        //Variable para almacenar el nobmre de usuario
        String nombre="";

        //Obtiene Base de datos.
        SQLiteDatabase db= conex.getWritableDatabase();

        //Consulta para obtener el nobmre de usuario
        String consulta="select nombre from usuario";

        //Cursor para consulta - ejecuta query
        Cursor fila=db.rawQuery(consulta,null);

        //Nombre, toma el primer nombre encontrado
        if (fila.moveToFirst()){
            nombre=fila.getString(0);
        }else{
            nombre="";
        }
        fila.close();
        db.close();

        return nombre;
    }
}
