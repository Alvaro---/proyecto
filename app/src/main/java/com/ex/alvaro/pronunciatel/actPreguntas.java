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
import Clases.Puntuacion;

/**
 * Created by Alvaro on 13/04/2015.
 */
public class actPreguntas extends Activity {

    Button btnPronunciar, btnContinuar, btnAtras;
    ImageView imagenRespuesta;
    TextView lblPregunta, lblPuntos;

    public static Context con;
    ArrayList<String> palabrasReconocidas;
    String text;

    String REPETIR_PRONUNCIACION="¿Puedes intentarlo de nuevo?";
    String CORRECTO="Correcto. Continuemos con la siguiente imagen.";
    String INCORRECTO="Esa no es la respuesta que esperaba. Intenta de nuevo.";
    String NUEVA_PREGUNTA="Otra pregunta";

    Intent sReconocerVoz;
    Handler espera=new Handler();

    ActPreguntas pregunta=ActPreguntas.getInstanciaActPreguntas(con);

    int a=0;
    float act;
    int repeticiones=0;
    int idPregunta=-1;
    String palabraPronunciad;


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

        //MOSTRAR PREGUNTA
        String preg=pregunta.getPregunta();
        lblPregunta.setText(preg);
        actMenuPrincipal.speak(preg);
        repeticiones=0;
    }

    private void cargarElementosInterfaz() {
        btnPronunciar=(Button)findViewById(R.id.btnPronunciarRespuesta);
        imagenRespuesta=(ImageView)findViewById(R.id.imageViewImagenPregunta);
        lblPregunta=(TextView)findViewById(R.id.lblPregunta);
        btnContinuar=(Button)findViewById(R.id.btnContinuarPregunta);
        btnAtras=(Button)findViewById(R.id.btnAtrasPreguntas);
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
                        text = reconocerVoz.getTexto();
                        palabrasReconocidas=reconocerVoz.getResultados();
                        boolean c=false;
                        if (text!=""){
                            for (int i=0;i<palabrasReconocidas.size();i++){
                                if (palabrasReconocidas.get(i).toString().equals(pregunta.getPalabraObjetivo())){
                                    palabraPronunciad=pregunta.getPalabraObjetivo();
                                    a=i;
                                    c=true;
                                }else if (palabrasReconocidas.get(i).toString().equals(pregunta.getPalabraObjetivo2())){
                                    palabraPronunciad=pregunta.getPalabraObjetivo2();
                                    a=i;
                                    c=true;
                                }
                            }
                            dialogoEscucha.dismiss();
                            if (!c){
                                mostrarResultado(REPETIR_PRONUNCIACION);
                                actMenuPrincipal.speak(REPETIR_PRONUNCIACION);
                                repeticiones++;
                            }else{
                                actMenuPrincipal.speak(CORRECTO + ". ");
                                mostrarResultado(CORRECTO);
                            }
                            c=false;

                        }else {
                            //Si reconoce sonido, pero no la respuesta.
                            actMenuPrincipal.speak(INCORRECTO);
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
                actuamizarImagen(false);
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imagenRespuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actMenuPrincipal.speak(pregunta.getPregunta());
            }
        });
    }

    private void mostrarResultado(String mostrar) {
        if (mostrar.equals(CORRECTO)) {
            calificar();
            btnContinuar.setText("CONTINUAR");
            actuamizarImagen(true);
        }
        else
            Toast.makeText(con, mostrar, Toast.LENGTH_LONG).show();
    }

    private void actuamizarImagen(boolean b) {
        if (b){
            int resId=getResources().getIdentifier(pregunta.getImagen(),"drawable",getPackageName());
            imagenRespuesta.setImageResource(resId);
            lblPregunta.setText("");
        }else
            imagenRespuesta.setImageResource(0);
    }

    private int calificar() {
        Puntuacion p=new Puntuacion (a);
        act=p.unaPregunta(repeticiones,"imagenes", idPregunta, palabraPronunciad);
        return 5;
    }


}
