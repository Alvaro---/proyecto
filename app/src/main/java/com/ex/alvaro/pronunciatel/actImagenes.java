package com.ex.alvaro.pronunciatel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Clases.ActImagenes;
import Clases.Puntuacion;

/**
 * Created by Alvaro on 07/04/2015.
 */
public class actImagenes extends Activity {

    public static Context con;
    Handler espera=new Handler();

    ImageView imvImagen;
    Button btnPronunciar, btnEjemplo, btnAtras;
    ImageButton btnRecargar;
    TextView lblInstruccion;

    Intent sReconocerVoz;
    ArrayList<String> palabrasReconocidas;
    String text; //Texto Reconocido

    String LOG_TAG="ACTIVIDAD IMAGENES";
    String REPETIR_PRONUNCIACION="No entendí bien. ¿Puedes intentarlo de nuevo?";
    String INCORRECTO="Creo que esa no es la respuesta. Intenta de nuevo";
    String CORRECTO="Correcto";

    ActImagenes actividadImagenes=ActImagenes.getInstanciaActImagenes(con);

    int a=0;
    float act=0;

    int repeticiones=0;


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
        repeticiones=0;
        actividadImagenes.buscarAleatorio();

        //establecer imagen
        int resId=getResources().getIdentifier(actividadImagenes.getImagen(),"drawable",getPackageName());
        imvImagen.setImageResource(resId);

    }

    private void cargarElementosLayout() {
        imvImagen=(ImageView)findViewById(R.id.imageViewImagen);
        btnPronunciar=(Button)findViewById(R.id.btnPronunciarImagen);
        lblInstruccion=(TextView)findViewById(R.id.lblInstruccion);
        btnAtras=(Button)findViewById(R.id.btnAtrasImagenes);
        btnEjemplo=(Button)findViewById(R.id.btnPronunciarEjemploImagenes);

        btnRecargar=(ImageButton)findViewById(R.id.btnRecargarImagen);

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
                        text = reconocerVoz.getTexto();
                        palabrasReconocidas=reconocerVoz.getResultados();
                        Log.d(LOG_TAG, text);
                        boolean c=false;
                        if (text!=""){
                            //calificar pronunciacion
                            for (int i=0;i<palabrasReconocidas.size();i++){
                                if (compararResultado(i)){
                                    a=i;
                                    c=true;
                                }
                            }
                            dialogoEscucha.dismiss();
                            if (!c){
                                actMenuPrincipal.speak(INCORRECTO);
                                repeticiones=repeticiones+2;
                            /*    espera.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btnPronunciar.callOnClick();
                                    }
                                },3500);*/
                            }else{
                                repeticiones=0;
                                actMenuPrincipal.speak(CORRECTO+". "+actividadImagenes.getDetallePalabra());
                                mostrarResultado(CORRECTO);
                            }
                            c=false;
                        }else {
                            actMenuPrincipal.speak(REPETIR_PRONUNCIACION);
                            repeticiones=repeticiones+1;
                            dialogoEscucha.dismiss();
                            espera.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    btnPronunciar.callOnClick();
                                }
                            },3500);
                        }
                        stopService(sReconocerVoz);
                    }
                }, 3500);
                dialogoEscucha.show();

            }
        });

        btnEjemplo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actMenuPrincipal.speak(actividadImagenes.getPalabraObjetivo());
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lblInstruccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actMenuPrincipal.speak(lblInstruccion.getText().toString());
            }
        });

        btnRecargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagenAleatoria();
            }
        });
    }
    public boolean compararResultado(int i){
        if (palabrasReconocidas.get(i).toString().equals(actividadImagenes.getPalabraObjetivo())||
                palabrasReconocidas.get(i).toString().equals(actividadImagenes.getPalabraObjetivo2())||
                palabrasReconocidas.get(i).toString().equals(actividadImagenes.getPalabraObjetivo3())||
                palabrasReconocidas.get(i).toString().equals(actividadImagenes.getPalabraObjetivo4()))
            return true;
        else
            return false;
    }


    private void mostrarResultado(String mostrar) {
        if (mostrar.equals(CORRECTO))
            dialogoContinuar();
    }

    private void dialogoContinuar() {
        puntuar();

        final Dialog dialogoContinuar=new Dialog(this);
        dialogoContinuar.setTitle("Correcto: ");

        LayoutInflater li=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.dialogo_continuar, null, false);
        dialogoContinuar.setContentView(v);

        TextView lblPuntuacion=(TextView)dialogoContinuar.findViewById(R.id.lblPuntos);
        lblPuntuacion.setText(act+" pts");

        Button btnContinuar=(Button)dialogoContinuar.findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoContinuar.dismiss();
            }
        });
        cargarImagenAleatoria();
        dialogoContinuar.show();
    }

    private void puntuar() {
        Puntuacion p=new Puntuacion (a);
        act=p.unEjercicio(repeticiones,"imagenes");
    }
}
