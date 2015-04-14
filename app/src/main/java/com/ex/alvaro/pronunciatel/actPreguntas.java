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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Clases.ActPreguntas;

/**
 * Created by Alvaro on 13/04/2015.
 */
public class actPreguntas extends Activity {

    Button btnPronunciar, btnContinuar;
    ImageView imagenRespuesta;
    TextView lblPregunta, lblPuntos;

    public static Context con;
    ArrayList<String> palabrasReconocidas;

    String REPETIR_PRONUNCIACION="¿Puedes intentarlo de nuevo?";
    String CORRECTO="Correcto";
    String NUEVA_PREGUNTA="nueva pregunta (Empieza los puntos en 0)";

    Intent sReconocerVoz;
    Handler espera=new Handler();

    ActPreguntas pregunta=ActPreguntas.getInstanciaActPreguntas(con);

    int a=0;
    float act;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_preguntas);
        cargarElementosInterfaz();
        cargarEventosElementos();

        con=this;
        sReconocerVoz= new Intent(con, reconocerVoz.class);

        Log.v("PREGUTNA EN ACTIVIDAD: ", pregunta.getPregunta());
        actualizarPregunta();

    }

    private void actualizarPregunta() {
        btnContinuar.setText(NUEVA_PREGUNTA);
        pregunta.buscarAleatorio();

        //establecer pregunta
        String preg=pregunta.getPregunta();
        lblPregunta.setText(preg);
        actMenuPrincipal.speak(preg);
    }

    private void cargarElementosInterfaz() {
        btnPronunciar=(Button)findViewById(R.id.btnPronunciarRespuesta);
        imagenRespuesta=(ImageView)findViewById(R.id.imageViewImagenPregunta);
        lblPregunta=(TextView)findViewById(R.id.lblPregunta);
        btnContinuar=(Button)findViewById(R.id.btnContinuarPregunta);
        lblPuntos=(TextView)findViewById(R.id.lblpuntosPreguntas);
    }


    private void cargarEventosElementos() {
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
                        boolean c=false;
                        if (text!=""){
                            for (int i=0;i<palabrasReconocidas.size();i++){
                                if (palabrasReconocidas.get(i).toString().equals(pregunta.getPalabraObjetivo())||
                                        palabrasReconocidas.get(i).toString().equals(pregunta.getPalabraObjetivo2())){
                                    a=i;
                                    c=true;

                                    int resId=getResources().getIdentifier(pregunta.getImagen(),"drawable",getPackageName());
                                    imagenRespuesta.setImageResource(resId);
                                    lblPregunta.setText("");
                                }
                            }
                            dialogoEscucha.dismiss();
                            if (!c){
                                mostrarResultado(REPETIR_PRONUNCIACION);
                                actMenuPrincipal.speak(REPETIR_PRONUNCIACION);
                            }else{
                                actMenuPrincipal.speak(CORRECTO + ". ");
                                mostrarResultado(CORRECTO);
                            }
                            c=false;

                        }else {
                            actMenuPrincipal.speak(REPETIR_PRONUNCIACION);
                            dialogoEscucha.dismiss();
                        }
                        stopService(sReconocerVoz);
                    }
                },3500);
            dialogoEscucha.show();
            }
        });

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarPregunta();
                imagenRespuesta.setImageResource(0);
            }
        });
    }

    private void mostrarResultado(String mostrar) {
        if (mostrar.equals(CORRECTO)) {
            btnContinuar.setText("CONTINUAR");
            lblPuntos.setText("5");
        }
        else
            Toast.makeText(con, mostrar, Toast.LENGTH_LONG).show();
    }

    private int calificar() {
        return 5;
    }


}
