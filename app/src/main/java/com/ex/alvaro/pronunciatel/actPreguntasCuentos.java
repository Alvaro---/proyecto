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
    String REPETIR_PRONUNCIACION="Creo que no entendi bien. Puedes repetirlo?";

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
                    //calificar pronunciacion
                    for (int i = 0; i < palabrasReconocidas.size(); i++) {
                        if (palabrasReconocidas.get(i).toUpperCase().equals(actCuentos.cuento.getPreguntas().get(numPreg).getRespuesta().toUpperCase())) {
                            c = true;
                            break;
                        }
                    }
                    dialogoEscucha.dismiss();
                    if (!c) {
                        actMenuPrincipal.speak(INCORRECTO);
                        repeticiones = repeticiones + 2;
                    } else {
                        actMenuPrincipal.speak(CORRECTO + ". ");
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


    }


    private void cargarPregunta() {
        if (numPreg<3) {
            actCuentos.cuento.cargarPreguntas();
            pregunta.setText(actCuentos.cuento.getPreguntas().get(numPreg).getPregunta());

            int aleatorio=(int)Math.random()*4;
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
