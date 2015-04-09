package com.ex.alvaro.pronunciatel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import Clases.ActImagenes;

/**
 * Created by Alvaro on 07/04/2015.
 */
public class actImagenes extends Activity {

    public static Context con;
    Handler espera=new Handler();

    ImageView imvImagen;
    Button btnPronunciar;
    TextView lblInstruccion;

    Intent sReconocerVoz;
    ArrayList<String> palabrasReconocidas;

    String LOG_TAG="ACTIVIDAD IMAGENES";
    String REPETIR_PRONUNCIACION="¿Puedes intentarlo de nuevo?";

    ActImagenes actividadImagenes=ActImagenes.getInstanciaActImagenes(con);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_imagenes);
        con=getApplicationContext();
        cargarElementosLayout();
        sReconocerVoz= new Intent(con, reconocerVoz.class);
        cargarAcionesElementosLayout();

        cargarImagenAleatoria();
        espera.postDelayed(new Runnable() {
            @Override
            public void run() {
                actMenuPrincipal.speak(lblInstruccion.getText().toString());
            }
        },3000);

    }

    private void cargarImagenAleatoria() {
        actividadImagenes.buscarImagen();

        //establecer imagen
        int resId=getResources().getIdentifier(actividadImagenes.getImagen(),"drawable",getPackageName());
        imvImagen.setImageResource(resId);

    }

    private void cargarElementosLayout() {
        imvImagen=(ImageView)findViewById(R.id.imageViewImagen);
        btnPronunciar=(Button)findViewById(R.id.btnPronunciarImagen);
        lblInstruccion=(TextView)findViewById(R.id.lblInstruccion);


    }
    private void cargarAcionesElementosLayout() {
        btnPronunciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(sReconocerVoz);
                espera.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String text = reconocerVoz.getTexto();
                        palabrasReconocidas=reconocerVoz.getResultados();
                        Log.d(LOG_TAG, text);
                        int a=0;
                        if (text!=""){
                            //calificar pronunciacion
                            for (int i=0;i<palabrasReconocidas.size();i++){
                                if (palabrasReconocidas.equals(actividadImagenes.getPalabraObjetivo())){
                                    a=i;
                                }
                            }

                        }
                        else {
                            actMenuPrincipal.speak(REPETIR_PRONUNCIACION);
                        }
                        stopService(sReconocerVoz);
                    }
                }, 3500);

            }
        });
    }

}
