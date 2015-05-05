package SQLite_repos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ex.alvaro.pronunciatel.actMenuPrincipal;

import java.util.ArrayList;

import Clases.Usuario;
import Coneccion.Conexion;
import Repos.repoUsuario;

/**
 * Created by Alvaro on 04/03/2015.
 */
public class sqliteUsuario implements repoUsuario {


    //consultas
    String select="select nombre from usuario";

    @Override
    public String cargarUsuario() {
        //Obtiene la instancia de la conexcion, llamando al conexto del menu principal
        Conexion conex=Conexion.getInstance(actMenuPrincipal.con);

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
        Conexion conex=Conexion.getInstance(actMenuPrincipal.con);
        SQLiteDatabase db= conex.getWritableDatabase();

        ContentValues registro=new ContentValues();
        registro.put("nombre",u.getNombre());

        try {
            db.insertOrThrow("usuario", null, registro);
            return true;
        }catch (SQLiteConstraintException e){
            Log.v("SQLITEUSUARIO:", "NO GUARDO - USUARIO EXISTENTE");
            return false;
        }

    }

    @Override
    public ArrayList<String> cargarTodos() {
        Conexion conex=Conexion.getInstance(actMenuPrincipal.con);
        ArrayList<String> nombres=new ArrayList<String>();
        SQLiteDatabase db= conex.getWritableDatabase();

        //Consulta para obtener el nobmre de usuario
        String consulta=select;

        //Cursor para consulta - ejecuta query
        Cursor fila=db.rawQuery(consulta,null);

        //Nombre, toma el primer nombre encontrado
        if (fila.moveToFirst()){
            do{
                nombres.add(fila.getString(fila.getColumnIndex("nombre")));
            }while (fila.moveToNext());
        }else{
            nombres=null;
        }
        fila.close();
        db.close();
        return nombres;
    }

    @Override
    public void eliminarNombre(Usuario usuario) {
        Conexion conex=Conexion.getInstance(actMenuPrincipal.con);
        SQLiteDatabase db= conex.getWritableDatabase();

        ContentValues registro=new ContentValues();
        registro.put("nombre",usuario.getNombre());

        db.delete("usuario","nombre="+usuario.getNombre(),null);
    }

    @Override
    public boolean modificarUsuario(Usuario usuario, String nombreAnterior) {
        Conexion conex=Conexion.getInstance(actMenuPrincipal.con);
        SQLiteDatabase db= conex.getWritableDatabase();

        ContentValues registro=new ContentValues();
        registro.put("nombre",usuario.getNombre());

        db.update("usuario",registro,"nombre="+nombreAnterior,null);
        return false;
    }


}
