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

    String IMAGEN="Vamos a jugar, con unas imágenes";
    Button btnActImagenes, btnActPreguntas, btnActPintar, btnActLetras;
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
                Intent actIagenes =new Intent(actMenuActividades.this, actImagenes.class);
                actMenuPrincipal.speak(IMAGEN);
                startActivity(actIagenes);
            }
        });
    }

    private void cargarElementosInterfaz() {
        //Botones
        btnActImagenes=(Button)findViewById(R.id.btnActImagen);
        btnActPreguntas=(Button)findViewById(R.id.btnActPreguntas);
        btnActPintar=(Button)findViewById(R.id.btnActPintar);
        btnActLetras=(Button)findViewById(R.id.btnActLetras);

        //Labels
        lblActividades=(TextView)findViewById(R.id.lblSelectActividad);
    }
}
