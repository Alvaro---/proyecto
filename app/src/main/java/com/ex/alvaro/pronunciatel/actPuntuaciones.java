package com.ex.alvaro.pronunciatel;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import Clases.Puntuacion;
import config.itemListaPuntos;

/**
 * Created by Alvaro on 05/05/2015.
 */
public class actPuntuaciones extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_puntuacion);

        Puntuacion p=new Puntuacion();
        p.setIdUsuario(actMenuPrincipal.usuario.getId());

        ListView listaPuntos = (ListView) findViewById(R.id.listView);
        ArrayList<Puntuacion> itemsPuntos = p.cargarResultados();

        //obtenerItems();


        itemListaPuntos adapter = new itemListaPuntos(this, itemsPuntos);

        listaPuntos.setAdapter(adapter);


    }

   /* private ArrayList<Puntuacion> obtenerItems() {
        ArrayList<Puntuacion> items = new ArrayList<Puntuacion>();
        items.add(new Puntuacion("10/05","05:00",5,3,"palabra","actividad"));
        return items;

    }*/
}
