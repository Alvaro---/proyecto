package com.ex.alvaro.pronunciatel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.logging.Handler;

import Clases.Letra;
import config.itemListaLetras;

/**
 * Created by Alvaro on 13/04/2015.
 */
public class actLetras extends Activity {

    int numero1=0;
    int numero2=0;
    int numero3=0;
    int numero4=0;
    int numero5=0;

    String INSTRUCCION="Selecciona la letra que escuches al precionar el boton";
    String CORRECTO="Muy bien. Probemos otra letra";
    String INCORRECTO="Esa no es la letra. Escuchala de nuevo";

    Letra letraObjetivo;

    Dialog continuarDialog=null;

    ListView lv;
    android.os.Handler espera =new android.os.Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_letras);

        espera.postDelayed(new Runnable() {
            @Override
            public void run() {
                speak(INSTRUCCION);
            }
        },2000);

        lv = (ListView)findViewById(R.id.listView);
        cargarListaLetras();

        ImageButton reproducir=(ImageButton)findViewById(R.id.imageButonReproducir);
        reproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(letraObjetivo.getLetra());
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Letra seleccion= (Letra)parent.getItemAtPosition(position);
                if (seleccion.getLetra().equals(letraObjetivo.getLetra())) {
                    speak(letraObjetivo.getLetra()+" de "+letraObjetivo.getEjemplo()+". "+CORRECTO);
                    dialogoCorrecto();
                }
                else speak(INCORRECTO);
            }
        });
    }

    private void cargarListaLetras() {
        ArrayList<Letra> itemsLetras = obtenerItems();
        itemListaLetras adapter = new itemListaLetras(this, itemsLetras);
        lv.setAdapter(adapter);
        int objetivo=elegirObjetivo(obtenerObjetivo());
        letraObjetivo=obtenerLetra(objetivo);
    }

    private void dialogoCorrecto() {
        continuarDialog=new Dialog(this);
        continuarDialog.setTitle("Correcto");
        continuarDialog.setCancelable(false);

        LayoutInflater li=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.dialogo_correcto_letras, null, false);
        continuarDialog.setContentView(v);

        Button btnContinuar=(Button)continuarDialog.findViewById(R.id.btnCorrectoLetras);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarListaLetras();
                continuarDialog.dismiss();
            }
        });
        continuarDialog.show();
    }

    private int elegirObjetivo(int objetivo) {
        if (objetivo==1)
            return numero1;
        else if (objetivo==2)
            return numero2;
        else if (objetivo==3)
            return numero3;
        else if (objetivo==4)
            return numero4;
        else return numero5;
    }

    private int obtenerObjetivo() {
        return (int) (Math.random()*5);
    }

    private ArrayList<Letra> obtenerItems() {
        ArrayList<Letra> items = new ArrayList<Letra>();

        numero1=obtenerAleatorio();
        do {
            numero2=obtenerAleatorio();
        }while (numero1==numero2);
        do {
            numero3=obtenerAleatorio();
        }while (numero1==numero2||numero2==numero3||numero1==numero3);
        do {
            numero4=obtenerAleatorio();
        }while (numero1==numero2||numero2==numero3||numero1==numero3||numero4==numero1||numero4==numero2||numero4==numero3);
        do {
            numero5=obtenerAleatorio();
        }while (numero1==numero2||numero2==numero3||numero1==numero3||numero4==numero1||numero4==numero2||numero4==numero3||numero5==numero1||numero5==numero2||numero5==numero3||numero5==numero4);

        Log.v("VER NUMEROS LETRAS: ",numero1+" "+numero2+" "+numero3+" "+numero4+" "+numero5);

        items.add(obtenerLetra(numero1));
        items.add(obtenerLetra(numero2));
        items.add(obtenerLetra(numero3));
        items.add(obtenerLetra(numero4));
        items.add(obtenerLetra(numero5));

        return items;
    }

    private Letra obtenerLetra(int i) {
        Letra l;
        if (i==1)
            l=new Letra("A","Abeja","drawable/letraa","drawable/ejletraa");
        else if (i==2)
            l=new Letra("B","Burro","drawable/letrab","drawable/ejletrab");
        else if (i==3)
            l=new Letra("C","Castillo","drawable/letrac","drawable/ejletrac");
        else if (i==4)
            l=new Letra("D","Delfin","drawable/letrad","drawable/ejletrad");
        else if (i==5)
            l=new Letra("E","Elefante","drawable/letrae","drawable/ejletrae");
        else if (i==6)
            l=new Letra("F","Fantasma","drawable/letraf","drawable/ejletraf");
        else if (i==7)
            l=new Letra("G","Girasol","drawable/letrag","drawable/ejletrag");
        else if (i==8)
            l=new Letra("H","Hipopotamo","drawable/letrah","drawable/ejletrah");
        else if (i==9)
            l=new Letra("I","Iglu","drawable/letrai","drawable/ejletrai");
        else if (i==10)
            l=new Letra("J","Jinete","drawable/letraj","drawable/ejletraj");
        else if (i==11)
            l=new Letra("K","Koala","drawable/letrak","drawable/ejletrak");
        else if (i==12)
            l=new Letra("L","Lobo","drawable/letral","drawable/ejletral");
        else if (i==13)
            l=new Letra("M","Motocicleta","drawable/letram","drawable/ejletram");
        else if (i==14)
            l=new Letra("N","Naranja","drawable/letran","drawable/ejletran");
        else if (i==15)
            l=new Letra("O","Oveja","drawable/letrao","drawable/ejletrao");
        else if (i==16)
            l=new Letra("P","Paraguas","drawable/letrap","drawable/ejletrap");
        else if (i==17)
            l=new Letra("Q","Queso","drawable/letraq","drawable/ejletraq");
        else if (i==18)
            l=new Letra("R","Reloj","drawable/letrar","drawable/ejletrar");
        else if (i==19)
            l=new Letra("S","Serpiente","drawable/letras","drawable/ejletras");
        else if (i==20)
            l=new Letra("T","Tesoro","drawable/letrat","drawable/ejletrat");
        else if (i==21)
            l=new Letra("Uu","Unicornio","drawable/letrau","drawable/ejletrau");
        else if (i==22)
            l=new Letra("V","Vaca","drawable/letrav","drawable/ejletrav");
        else if (i==23)
            l=new Letra("W","Waterpolo","drawable/letraw","drawable/ejletraw");
        else if (i==24)
            l=new Letra("X","Xilofono","drawable/letrax","drawable/ejletrax");
        else if (i==25)
            l=new Letra("Y","Yogurt","drawable/letray","drawable/ejletray");
        else if (i==27)
            l=new Letra("Ñ","Bebe","drawable/letrann","drawable/ejletrann");
        else
            l=new Letra("Z","Zapatos","drawable/letraz","drawable/ejletraz");
        return l;
    }


    private int obtenerAleatorio() {
        return (int) (Math.random()*27);
    }


    public void speak(String leer1){
        Intent service = new Intent(getApplicationContext(), leerTTS.class);
        service.putExtra("leeme", leer1);
        getApplicationContext().startService(service);
    }
}
