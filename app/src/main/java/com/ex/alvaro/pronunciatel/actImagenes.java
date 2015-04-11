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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    String CORRECTO="Correcto";

    ActImagenes actividadImagenes=ActImagenes.getInstanciaActImagenes(con);

    int a=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_imagenes);
        con=this;
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
                final Dialog dialogoEscucha=new Dialog(con);
                dialogoEscucha.setTitle("Escuchando...");
                dialogoEscucha.setContentView(R.layout.dialogo_escuchando);

                startService(sReconocerVoz);
                espera.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String text = reconocerVoz.getTexto();
                        palabrasReconocidas=reconocerVoz.getResultados();
                        Log.d(LOG_TAG, text);
                        boolean c=false;
                        if (text!=""){
                            //calificar pronunciacion
                            for (int i=0;i<palabrasReconocidas.size();i++){
                                if (palabrasReconocidas.get(i).toString().equals(actividadImagenes.getPalabraObjetivo())){
                                    a=i;
                                    c=true;
                                }
                            }
                            Log.d(LOG_TAG, a+"");
                            dialogoEscucha.dismiss();
                            if (!c){
                                mostrarResultado(REPETIR_PRONUNCIACION);
                                actMenuPrincipal.speak(REPETIR_PRONUNCIACION);
                            }else{
                                mostrarResultado(CORRECTO);
                                actMenuPrincipal.speak(CORRECTO+". "+actividadImagenes.getDetallePalabra());
                            }
                            c=false;
                        }else {
                            actMenuPrincipal.speak(REPETIR_PRONUNCIACION);
                            actMenuPrincipal.speak(REPETIR_PRONUNCIACION);
                            dialogoEscucha.dismiss();
                        }
                        stopService(sReconocerVoz);
                    }
                }, 3500);
                dialogoEscucha.show();

            }
        });
    }

    private void mostrarResultado(String mostrar) {
        if (mostrar.equals(CORRECTO))
            dialogoContinuar ();
        else
            Toast.makeText(con,mostrar, Toast.LENGTH_LONG).show();
    }

    private void dialogoContinuar() {
        cargarImagenAleatoria();
    }

}
