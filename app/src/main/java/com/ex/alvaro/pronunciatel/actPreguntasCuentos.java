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
import android.widget.TextView;


import java.util.ArrayList;

import Clases.PreguntasCuento;
import Clases.Puntuacion;
import config.distanciaPalabras;

/**
 * Created by Alvaro on 21/05/2015.
 */
public class actPreguntasCuentos extends Activity {

    TextView pregunta, respuesta1, respuesta2,respuesta3;
    Button btnContestar;
    public static Context con;

    Handler espera=new Handler();
    Intent sReconocerVoz;

    int numPreg=0;

    //Texto Reconocido
    String text;
    ArrayList<String> palabrasReconocidas;
    int repeticiones=0;

    String INCORRECTO="Creo que esa no es la respuesta correcta. Intenta de nuevo";
    String CORRECTO="Correcto";
    String REPETIR_PRONUNCIACION="No entendi bien. Puedes repetirlo?";

    int aleatorio;

    int puntosCorrecto;
    String palabraPronunciada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_preguntas_cuentos);
        cargarElmenteosInterfaz();

        con=getApplicationContext();
        sReconocerVoz= new Intent(con, reconocerVoz.class);
        cargarPregunta();
        
        pregunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(pregunta.getText().toString());
            }
        });
        
        respuesta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(respuesta1.getText().toString());
            }
        });
        respuesta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(respuesta2.getText().toString());
            }
        });
        respuesta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(respuesta3.getText().toString());
            }
        });
        
        btnContestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDialogoContestar();
            }
        });

        speak(pregunta.getText().toString());
        
    }

    private void abrirDialogoContestar() {
        final Dialog dialogoEscucha=new Dialog(actPreguntasCuentos.this);
        dialogoEscucha.setTitle("Escuchando...");
        dialogoEscucha.setContentView(R.layout.dialogo_escuchando);

        startService(sReconocerVoz);

        espera.postDelayed(new Runnable() {
            @Override
            public void run() {
                text = reconocerVoz.getTexto();
                palabrasReconocidas = reconocerVoz.getResultados();
                Log.d("PREGUNTAS CUENTOS:", text);
                boolean c = false;
                if (text != "") {
                    int valorHas1=10;
                    //calificar pronunciacion
                    for (int i = 0; i < palabrasReconocidas.size(); i++) {
                        if (palabrasReconocidas.get(i).toUpperCase().equals(actCuentos.cuento.getPreguntas().get(numPreg).getRespuesta().toUpperCase())) {
                            c = true;
                            puntosCorrecto=i;
                            palabraPronunciada=actCuentos.cuento.getPreguntas().get(numPreg).getRespuesta().toUpperCase()+" - "+palabrasReconocidas.get(i).toUpperCase();
                            break;
                        }else{
                            int valorComprado= distanciaPalabras.LevenshteinDistance(palabrasReconocidas.get(i).toUpperCase(),actCuentos.cuento.getPreguntas().get(numPreg).getRespuesta().toUpperCase());
                            if (valorComprado<5){
                                if (valorHas1>valorComprado) {
                                    valorHas1 = valorComprado;
                                    puntosCorrecto=valorHas1*5;
                                    palabraPronunciada=actCuentos.cuento.getPreguntas().get(numPreg).getRespuesta().toUpperCase()+" - "+palabrasReconocidas.get(i).toUpperCase();
                                }
                                c=true;
                            }

                        }
                    }
                    dialogoEscucha.dismiss();
                    if (!c) {
                        actMenuPrincipal.speak(INCORRECTO);
                        repeticiones = repeticiones + 2;
                    } else {
                        actMenuPrincipal.speak(CORRECTO + ".");
                        mostrarResultado(CORRECTO);
                    }
                    c = false;
                } else {
                    actMenuPrincipal.speak(REPETIR_PRONUNCIACION);
                    repeticiones = repeticiones + 1;
                    dialogoEscucha.dismiss();
                }
                stopService(sReconocerVoz);
            }
        }, 3500);
        dialogoEscucha.show();
    }

    private void mostrarResultado(String correcto) {
        if (correcto.equals(CORRECTO)){
            numPreg++;
            cargarPregunta();
            Puntuacion p=new Puntuacion();
            float puntos=p.guardarPuntosCuentos(puntosCorrecto, actCuentos.cuento.getId(), palabraPronunciada);

        }
    }


    private void cargarPregunta() {
        if (numPreg<3) {
            actCuentos.cuento.cargarPreguntas();
            pregunta.setText(actCuentos.cuento.getPreguntas().get(numPreg).getPregunta());
            if (numPreg>0)
                speak("Correcto. "+pregunta.getText().toString());
            else
                speak(pregunta.getText().toString());
            aleatorio=(int)(Math.random()*4);
            System.out.println(aleatorio);
            if (aleatorio==0) {
                respuesta1.setText(actCuentos.cuento.getPreguntas().get(numPreg).getRespuesta());
                respuesta2.setText(actCuentos.cuento.getPreguntas().get(numPreg).getRespuesta2());
                respuesta3.setText(actCuentos.cuento.getPreguntas().get(numPreg).getRespuesta3());
            }else if(aleatorio==1){
                respuesta1.setText(actCuentos.cuento.getPreguntas().get(numPreg).getRespuesta());
                respuesta3.setText(actCuentos.cuento.getPreguntas().get(numPreg).getRespuesta2());
                respuesta2.setText(actCuentos.cuento.getPreguntas().get(numPreg).getRespuesta3());
            }else if(aleatorio==2){
                respuesta2.setText(actCuentos.cuento.getPreguntas().get(numPreg).getRespuesta());
                respuesta1.setText(actCuentos.cuento.getPreguntas().get(numPreg).getRespuesta2());
                respuesta3.setText(actCuentos.cuento.getPreguntas().get(numPreg).getRespuesta3());
            }else if(aleatorio==3){
                respuesta3.setText(actCuentos.cuento.getPreguntas().get(numPreg).getRespuesta());
                respuesta2.setText(actCuentos.cuento.getPreguntas().get(numPreg).getRespuesta2());
                respuesta1.setText(actCuentos.cuento.getPreguntas().get(numPreg).getRespuesta3());
            }
        }else{
            Intent intent=new Intent(actPreguntasCuentos.this, actCuentos.class);
            startActivity(intent);
        }

    }

    private void cargarElmenteosInterfaz() {
        pregunta=(TextView)findViewById(R.id.lblPreguntaCuento);
        respuesta1=(TextView)findViewById(R.id.respuesta1);
        respuesta2=(TextView)findViewById(R.id.respuesta2);
        respuesta3=(TextView)findViewById(R.id.respuesta3);
        btnContestar=(Button)findViewById(R.id.btnContestarPreguntaCuento);
    }

    public void speak(String leer1){
        Intent service = new Intent(getApplicationContext(), leerTTS.class);
        service.putExtra("leeme",leer1);
        getApplicationContext().startService(service);
    }
}
