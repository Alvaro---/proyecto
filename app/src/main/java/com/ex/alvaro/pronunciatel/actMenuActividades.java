package com.ex.alvaro.pronunciatel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Alvaro on 06/04/2015.
 */
public class actMenuActividades extends Activity{

    //FRASES PARA LEER
    String IMAGEN="Vamos a jugar, con unas imágenes";
    String PREGUNTAS="¿Puedes responder estas preguntas?";
    String LETRAS="Repasemos las letras";
    String PINTAR="Pintemos";
    String CUENTOS="Leamos cuentos";
    String OPUESTOS="¿Puedes ver identificar la palabra opuesta?";

    Button btnActImagenes, btnActPreguntas, btnActPintar, btnActLetras, btnAtras, btnCuentos, btnOpuestos;
    TextView lblActividades;
    Handler espera=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu_actividades);
        cargarElementosInterfaz();
        accionesBotones();

        espera.postDelayed(new Runnable() {
            @Override
            public void run() {
                  actMenuPrincipal.speak(lblActividades.getText().toString());
            }
        },2000);
    }


    /******************************ELEMENTOS DE INTERFAZ ***********************/

    private void accionesBotones() {
        btnActImagenes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actMenuPrincipal.speak(IMAGEN);
                Intent actividad =new Intent(actMenuActividades.this, actImagenes.class);
                //actMenuPrincipal.speak(IMAGEN);
                startActivity(actividad);
            }
        });

        btnActPreguntas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actMenuPrincipal.speak(PREGUNTAS);
                Intent acttividad =new Intent(actMenuActividades.this, actPreguntas.class);
                startActivity(acttividad);
            }
        });

        btnActLetras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actMenuPrincipal.speak(LETRAS);
                Intent actividad =new Intent(actMenuActividades.this, actLetras.class);
                startActivity(actividad);
            }
        });

        btnActPintar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actMenuPrincipal.speak(PINTAR);
                Intent actividad =new Intent(actMenuActividades.this, actPintar.class);
                startActivity(actividad);
            }
        });

        btnCuentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actMenuPrincipal.speak(CUENTOS);
                Intent actividad =new Intent(actMenuActividades.this, actCuentos.class);
                startActivity(actividad);
            }
        });

        btnOpuestos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actMenuPrincipal.speak(OPUESTOS);
                Intent actividad =new Intent(actMenuActividades.this, actOpuestos.class);
                startActivity(actividad);
            }
        });



        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void cargarElementosInterfaz() {
        //Botones
        btnActImagenes=(Button)findViewById(R.id.btnActImagen);
        btnActPreguntas=(Button)findViewById(R.id.btnActPreguntas);
        btnActPintar=(Button)findViewById(R.id.btnActPintar);
        btnActLetras=(Button)findViewById(R.id.btnActLetras);
        btnAtras=(Button)findViewById(R.id.btnAtrasActividades);

        btnCuentos=(Button)findViewById(R.id.btnActCuentos);
        btnOpuestos=(Button)findViewById(R.id.btnActAntonimos);
        //Labels
        lblActividades=(TextView)findViewById(R.id.lblSelectActividad);
    }

}
