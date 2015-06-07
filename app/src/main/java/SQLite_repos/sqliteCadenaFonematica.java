package SQLite_repos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ex.alvaro.pronunciatel.actImagenes;

import java.sql.SQLOutput;

import Clases.cadenaFonematica;
import Coneccion.Conexion;
import Repos.repoCadenaFonematica;

/**
 * Created by HP on 01/06/2015.
 */
public class sqliteCadenaFonematica implements repoCadenaFonematica {

    @Override
    public boolean tieneInicial(cadenaFonematica cad) {
        Conexion conex=Conexion.getInstance(actImagenes.con);
        SQLiteDatabase db= conex.getWritableDatabase();

        boolean existe=false;
        String consulta="select inicial from cadena where letra='"+cad.getLetra()+"'";

        Cursor fila=db.rawQuery(consulta,null);
        if (fila.moveToFirst()){
            while (fila.moveToNext()){
                if (fila.getInt(0)==1)
                    existe=true;
            }
        }
        return existe;
    }

    @Override
    public boolean tieneMedia(cadenaFonematica cad) {
        Conexion conex=Conexion.getInstance(actImagenes.con);
        SQLiteDatabase db= conex.getWritableDatabase();

        boolean existe=false;
        String consulta="select media from cadena where letra='"+cad.getLetra()+"'";

        Cursor fila=db.rawQuery(consulta,null);
        if (fila.moveToFirst()){
            while (fila.moveToNext()){
                if (fila.getInt(0)==1)
                    existe=true;
            }
        }
        return existe;

    }

    @Override
    public boolean tieneFinal(cadenaFonematica cad) {
        Conexion conex=Conexion.getInstance(actImagenes.con);
        SQLiteDatabase db= conex.getWritableDatabase();

        boolean existe=false;
        String consulta="select final from cadena where letra='"+cad.getLetra()+"'";

        Cursor fila=db.rawQuery(consulta,null);
        if (fila.moveToFirst()){
            while (fila.moveToNext()){
                if (fila.getInt(0)==1)
                    existe=true;
            }
        }
        return existe;
    }

    @Override
    public boolean tieneInversa(cadenaFonematica cad) {
        Conexion conex=Conexion.getInstance(actImagenes.con);
        SQLiteDatabase db= conex.getWritableDatabase();

        boolean existe=false;
        String consulta="select inversa from cadena where letra='"+cad.getLetra()+"'";

        Cursor fila=db.rawQuery(consulta,null);
        if (fila.moveToFirst()){
            while (fila.moveToNext()){
                if (fila.getInt(0)==1)
                    existe=true;
            }
        }
        return existe;
    }

    @Override
    public cadenaFonematica cargarImagen(cadenaFonematica cadenaFonematica) {
        Conexion conex=Conexion.getInstance(actImagenes.con);
        SQLiteDatabase db= conex.getWritableDatabase();

        cadenaFonematica cad=new cadenaFonematica(cadenaFonematica.getLetra());
        cad.setPosicione(cadenaFonematica.getPosicione());
        String consulta="";
        if (cad.getPosicione().equals("any")){
            consulta="select imagen, palabra  from cadena where letra='"+cad.getLetra()+"'";
        }else {
            consulta = "select imagen, palabra  from cadena where letra='" + cad.getLetra() + "' and " + cad.getPosicione() + "=1";
        }
        Cursor fila=db.rawQuery(consulta, null);
        int random= (int) (Math.random()*fila.getCount());
        if (fila.moveToFirst()){
            for (int i=0;i<random;i++){
                fila.moveToNext();
            }
            System.out.println(fila.getString(0));
            cad.setImagen(fila.getString(0));
            cad.setPalabra(fila.getString(1));
        }
        return cad;
    }

}
