package com.ex.alvaro.pronunciatel;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import Clases.cadenaFonematica;

/**
 * Created by HP on 31/05/2015.
 */
public class actSleccionaLetra extends Activity implements AdapterView.OnItemClickListener {

    ListView lista;
    ArrayList<String> vocales=new ArrayList<String>(4);
    ArrayList<String> consonantes=new ArrayList<String>(18);
    ArrayList<String> combinadas=new ArrayList<String>(12);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_seleccion_letras);
        lista=(ListView)findViewById(R.id.ListaSeleccionLetras);
        iniciarArrayLists();
        Bundle bundle=getIntent().getExtras();
        int codigoCarga=bundle.getInt("select");
        if (codigoCarga==1)
            cargarLissta(vocales);
        else if (codigoCarga==2)
            cargarLissta(consonantes);
        else cargarLissta(combinadas);

        lista.setOnItemClickListener(this);

    }

    private void iniciarArrayLists() {
        //VOCALES
        vocales.add("A");
        vocales.add("E");
        vocales.add("I");
        vocales.add("O");
        vocales.add("U");

        //CONSONANTES
        consonantes.add("B-V");
        consonantes.add("C-Q-K");
        consonantes.add("CH");
        consonantes.add("D");
        consonantes.add("F");
        consonantes.add("G");
        consonantes.add("J");
        consonantes.add("C-S-Z");
        consonantes.add("L");
        consonantes.add("M");
        consonantes.add("N");
        consonantes.add("Ñ");
        consonantes.add("P");
        consonantes.add("R");
        consonantes.add("RR");
        consonantes.add("T");
        consonantes.add("X");
        consonantes.add("Y");

        //COMBINADAS
        combinadas.add("pl");
        combinadas.add("bl");
        combinadas.add("fl");
        combinadas.add("cl");
        combinadas.add("gl");
        combinadas.add("tl");
        combinadas.add("pr");
        combinadas.add("br");
        combinadas.add("fr");
        combinadas.add("cr");
        combinadas.add("gr");
        combinadas.add("tr");
        combinadas.add("dr");
    }

    private void cargarLissta(ArrayList<String> letras) {
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.listacentrada, letras);
        lista.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String letra = parent.getItemAtPosition(position).toString();
        final cadenaFonematica cadena=new cadenaFonematica(letra);
        ArrayList<String> posiciones = cadena.obtenerArrayPosiciones();
        try{
            if (posiciones.size()>0){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Posicion de la letra");

                //MOSTRAR LISTA POSIBLES POSICIONES
                ListView modeList = new ListView(this);
                ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(this, R.layout.listacentrada, android.R.id.text1, posiciones);
                modeList.setAdapter(modeAdapter);

                modeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(actSleccionaLetra.this, actmostrarImagenCadena.class);
                        intent.putExtra("letra", cadena.getLetra());
                        intent.putExtra("seleccion", parent.getItemAtPosition(position).toString());
                        startActivity(intent);

                    }
                });

                builder.setView(modeList);
                final Dialog dialog = builder.create();

                dialog.show();

            } else {
                Intent intent=new Intent(actSleccionaLetra.this, actmostrarImagenCadena.class);
                intent.putExtra("letra", cadena.getLetra());
                intent.putExtra("seleccion", "any");
                startActivity(intent);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
