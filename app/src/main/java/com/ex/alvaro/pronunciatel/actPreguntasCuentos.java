package com.ex.alvaro.pronunciatel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Clases.PreguntasCuento;

/**
 * Created by Alvaro on 21/05/2015.
 */
public class actPreguntasCuentos extends Activity {

    TextView pregunta, respuesta1, respuesta2,respuesta3;
    Button btnContestar;
    public static Context con;

    int numPreg=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_preguntas_cuentos);
        cargarElmenteosInterfaz();

        con=getApplicationContext();
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

    }

    private void cargarPregunta() {
        actCuentos.cuento.cargarPreguntas();
        System.out.println(actCuentos.cuento.getPreguntas().size());
        pregunta.setText(actCuentos.cuento.getPreguntas().get(numPreg).getPregunta());
        respuesta1.setText(actCuentos.cuento.getPreguntas().get(numPreg).getRespuesta());
        respuesta2.setText(actCuentos.cuento.getPreguntas().get(numPreg).getRespuesta2());
        respuesta3.setText(actCuentos.cuento.getPreguntas().get(numPreg).getRespuesta3());

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
        service.putExtra("leeme", leer1);
        getApplicationContext().startService(service);
    }
}
