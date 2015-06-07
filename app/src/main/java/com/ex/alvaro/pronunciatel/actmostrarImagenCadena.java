package com.ex.alvaro.pronunciatel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

import Clases.Puntuacion;
import Clases.cadenaFonematica;

/**
 * Created by HP on 02/06/2015.
 */
public class actmostrarImagenCadena extends Activity {

    ImageView imagen;
    Button btnPronunciar, btnEjemplo;
    ImageButton btncambiarImagen;

    Intent sReconocerVoz;
    ArrayList<String> palabrasReconocidas;
    String text;
    public static Context con;
    Handler espera=new Handler();

    cadenaFonematica cadena;

    Puntuacion puntos=new Puntuacion();
    float punt=0;

    String letraActual;
    String posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_imagenes);
        Bundle bundle=getIntent().getExtras();
        letraActual=bundle.getString("letra");
        posicion=bundle.getString("seleccion");
        cadena=new cadenaFonematica(letraActual);
        cadena.setPosicione(posicion);
        con=this;

        cargarElementossInterfaz();
        sReconocerVoz= new Intent(con, reconocerVoz.class);
        accionesBotones();
        cargarImagenAleatoria();
    }

    private void accionesBotones() {
        btncambiarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagenAleatoria();
            }
        });

        btnPronunciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialogoEscucha = new Dialog(con);
                dialogoEscucha.setTitle("Escuchando...");
                dialogoEscucha.setContentView(R.layout.dialogo_escuchando);

                startService(sReconocerVoz);
                espera.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        text = reconocerVoz.getTexto();
                        palabrasReconocidas = reconocerVoz.getResultados();
                        Log.d("Mostrar Imagen Cadena Fonematica", text);
                        boolean c = false;
                        if (text != "") {
                            //calificar pronunciacion
                            for (int i = 0; i < palabrasReconocidas.size(); i++) {
                                if (compararResultado(i)) {
                                    cargarImagenAleatoria();
                                    c = true;
                                    break;
                                }
                            }
                            dialogoEscucha.dismiss();
                            if (!c) {
                                actMenuPrincipal.speak("Bien");
                                punt++;
                                puntos.guardarAcumuladoCadenaFonematica(punt, letraActual, posicion);
                                cargarImagenAleatoria();

                                //puntosDeHoy--;
                            } else {
                                actMenuPrincipal.speak("Muy Bien. Adelante");
                            }
                            c = false;
                        } else {
                            actMenuPrincipal.speak("No entendi bien, puedes repetirlo");
                            dialogoEscucha.dismiss();
                        }
                        stopService(sReconocerVoz);
                    }
                }, 3500);
                dialogoEscucha.show();

            }
        });
    }

    private boolean compararResultado(int i) {
        String palabracercana;
        if (palabrasReconocidas.get(i).toString().equals(cadena.getPalabra()))
            return true;
        return false;
    }

    private void cargarElementossInterfaz() {
        btncambiarImagen=(ImageButton)findViewById(R.id.btnRecargarImagen);
        btnEjemplo=(Button)findViewById(R.id.btnPronunciarEjemploImagenes);
        btnPronunciar=(Button)findViewById(R.id.btnPronunciarImagen);
        imagen=(ImageView)findViewById(R.id.imageViewImagen);
    }

    private void cargarImagenAleatoria() {
        cadena.cambiarImagenObjetivo();
        int resId=getResources().getIdentifier(cadena.getImagen(),"drawable",getPackageName());
        imagen.setImageResource(resId);
    }
}
