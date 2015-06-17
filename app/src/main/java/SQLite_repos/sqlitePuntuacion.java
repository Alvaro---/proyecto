package SQLite_repos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ex.alvaro.pronunciatel.actMenuPrincipal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import Clases.Puntuacion;
import Coneccion.Conexion;
import Repos.repoPuntuacion;

/**
 * Created by Alvaro on 05/05/2015.
 */
public class sqlitePuntuacion implements repoPuntuacion {
    @Override
    public void guardarPuntuacion(float puntuacion, String actividad, int idPalabra, String palabraPronunciada, String hora, String fecha) {
        Conexion conex=Conexion.getInstance(actMenuPrincipal.con);
        SQLiteDatabase db= conex.getWritableDatabase();

        String fechaActual=fecha;
        String horaActual=hora;
        
        ContentValues registro=new ContentValues();
        registro.put("fecha",fechaActual);
        registro.put("hora", horaActual);
        registro.put("idUsuario", actMenuPrincipal.usuario.getId());
        registro.put("hora", horaActual);
        registro.put("idPalabra", idPalabra);
        registro.put("palabraPronunciada", palabraPronunciada);
        registro.put("puntuacion", puntuacion);
        Log.v("SQLITE:", "GUARDADO");
        try {
            db.insertOrThrow("puntuacionImagen", null, registro);
        }catch (SQLiteConstraintException e){
            Log.v("SQLITE:", "NO GUARDO - error puntuaciones");
        }
    }

    @Override
    public ArrayList<Puntuacion> cargarPuntuaciones(int idUsuario) {
        Conexion conex=Conexion.getInstance(actMenuPrincipal.con);
        ArrayList<Puntuacion> puntajes=new ArrayList<Puntuacion>();
        SQLiteDatabase db= conex.getWritableDatabase();

        //Consulta para obtener el nobmre de usuario
        String consulta="select fecha, hora, puntuacion as puntos, " +
                "idUsuario, palabraPronunciada, 'Imagenes' as actividad from puntuacionImagen" +
                " where idUsuario="+idUsuario+"" +
                " union " +
                "select fecha, hora, puntuacion as puntos, " +
                "idUsuario,palabraPronunciada, 'Preguntas' as actividad from puntuacionPreguntas" +
                " where idUsuario="+idUsuario+"";

        //Cursor para consulta - ejecuta query
        Cursor fila=db.rawQuery(consulta,null);

        //Nombre, toma el primer nombre encontrado
        if (fila.moveToFirst()){
            do{
                Puntuacion p=new Puntuacion();
                p.setFecha(fila.getString(fila.getColumnIndex("fecha")));
                p.setHora(fila.getString(fila.getColumnIndex("hora")));
                p.setPuntuacion(fila.getFloat(fila.getColumnIndex("puntos")));
                p.setIdUsuario(fila.getInt(fila.getColumnIndex("idUsuario")));
                p.setPalabra(fila.getString(fila.getColumnIndex("palabraPronunciada")));
                p.setActividad(fila.getString(fila.getColumnIndex("actividad")));

                puntajes.add(p);

            }while (fila.moveToNext());
        }else{
            puntajes=null;
        }
        fila.close();
        db.close();
        return puntajes;
    }

    @Override
    public void guardarPuntuacionPregunta(float puntuacion, String actividad, int idPregunta, String palabraPronunciad, String hora, String fecha) {
        Conexion conex=Conexion.getInstance(actMenuPrincipal.con);
        SQLiteDatabase db= conex.getWritableDatabase();

        String fechaActual=fecha;
        String horaActual=hora;

        ContentValues registro=new ContentValues();
        registro.put("fecha",fechaActual);
        registro.put("hora", horaActual);
        registro.put("idUsuario", actMenuPrincipal.usuario.getId());
        registro.put("hora", horaActual);
        registro.put("idPregunta", idPregunta);
        registro.put("palabraPronunciada", palabraPronunciad);
        registro.put("puntuacion", puntuacion);
        Log.v("SQLITE:", "GUARDADO");
        try {
            db.insertOrThrow("puntuacionPreguntas", null, registro);
        }catch (SQLiteConstraintException e){
            Log.v("SQLITE:", "NO GUARDO - error puntuaciones");
        }
    }

    @Override
    public void actualizaPuntosCadenaFonematica(float punt, String letraActual, String posicion) {

    }

    @Override
    public void guardarPuntosCuento(int puntosCorreccion, int idCuento, String palabraPronunciad, String fecha, String hora) {
        Conexion conex=Conexion.getInstance(actMenuPrincipal.con);
        SQLiteDatabase db= conex.getWritableDatabase();

        String fechaActual=fecha;
        String horaActual=hora;

        ContentValues registro=new ContentValues();
        registro.put("fecha",fechaActual);
        registro.put("hora", horaActual);
        registro.put("idUsuario", actMenuPrincipal.usuario.getId());
        registro.put("hora", horaActual);
        registro.put("idPregunta", idCuento);
        registro.put("palabraPronunciada", palabraPronunciad);
        registro.put("puntuacion", puntosCorreccion);
        Log.v("SQLITE:", "GUARDADO");
        try {
            db.insertOrThrow("puntuacionPreguntas", null, registro);
        }catch (SQLiteConstraintException e){
            Log.v("SQLITE:", "NO GUARDO - error puntuaciones");
        }
    }


}
