package com.ex.alvaro.pronunciatel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * Created by Alvaro on 13/04/2015.
 */
public class actPintar extends Activity {

    private Button btnCambiarColor;
    private ImageButton btnNuevo, btnPincel, btnBorrar, btnGuardar;
    private DrawingView drawView;
    private float smallBrush, mediumBrush, largeBrush;

    public static Context con;
    Handler espera=new Handler();

    Intent sReconocerVoz;
    ArrayList<String> palabrasReconocidas;
    String text;

    String INSTRUCCION="Puedes hacer un dibujo y guardarlo en tu movil.";
    String CAMBIOCOLOR="Pronuncia el color que deseas";
    String REPETIR_PRONUNCIACION="Puedes repetirlo";

    //ArrayList <String> colores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pintar);
        con=this;
        actMenuPrincipal.speak(INSTRUCCION);
        sReconocerVoz= new Intent(con, reconocerVoz.class);
        cargarElementoInterfaz();
        cargarAccionesBotones();


    }

    private void cargarAccionesBotones() {
        btnCambiarColor.setOnClickListener(new View.OnClickListener() {
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
                        if (text!=""){
                            buscarColorResultado();

                        }else {
                            actMenuPrincipal.speak(REPETIR_PRONUNCIACION);
                            dialogoEscucha.dismiss();
                        }
                        stopService(sReconocerVoz);
                    }
                },3000);
                dialogoEscucha.show();
            }
        });
    }

    private void buscarColorResultado() {


    }

    private void cargarElementoInterfaz() {
        drawView = (DrawingView)findViewById(R.id.drawing);
        btnBorrar = (ImageButton)findViewById(R.id.erase_btn);
        btnNuevo = (ImageButton)findViewById(R.id.new_btn);
        btnGuardar = (ImageButton)findViewById(R.id.save_btn);
        btnPincel = (ImageButton)findViewById(R.id.brush_btn);

        btnCambiarColor = (Button)findViewById(R.id.btnCambiarColor);

        //sizes
        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);
    }
}
