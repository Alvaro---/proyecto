package SQLite_repos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ex.alvaro.pronunciatel.menuPrincipal;

import Clases.Usuario;
import Coneccion.Conexion;
import Repos.repoUsuario;

/**
 * Created by Alvaro on 04/03/2015.
 */
public class sqliteUsuario implements repoUsuario {


    //consultas
    String select="select nombre from usuario";
    String insertUsu="insert into usuario (nombre)";

    @Override
    public String cargarUsuario() {
        //Obtiene la instancia de la conexcion, llamando al conexto del menu principal
        Conexion conex=Conexion.getInstance(menuPrincipal.con);

        //Variable para almacenar el nobmre de usuario
        String nombre="";

        //Obtiene Base de datos.
        SQLiteDatabase db= conex.getWritableDatabase();

        //Consulta para obtener el nobmre de usuario
        String consulta=select;

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

    @Override
    public boolean guardarUsuario(Usuario u) {
        Conexion conex=Conexion.getInstance(menuPrincipal.con);
        SQLiteDatabase db= conex.getWritableDatabase();
        String consulta=insertUsu+"values ('"+u.getNombre()+"')";
        return false;
    }
}
