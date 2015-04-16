package com.ex.alvaro.pronunciatel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
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
    String REPETIR_PRONUNCIACION="¿Puedes repetirlo?";

    //colores a reconocerse
    ArrayList<String> colores = new ArrayList<String>();

    Dialog dialogoColores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pintar);
        con=this;
        actMenuPrincipal.speak(INSTRUCCION);
        sReconocerVoz= new Intent(con, reconocerVoz.class);
        cargarElementoInterfaz();
        cargarAccionesBotones();
        cargarColores();


    }

    private void cargarColores() {
        colores.add("rojo");
        colores.add("rosado");
        colores.add("rosa");
        colores.add("amarillo");
        colores.add("purpura");
        colores.add("violeta");
        colores.add("plomo");
        colores.add("café");
        colores.add("azul");
        colores.add("verde");
        colores.add("naranja");
        colores.add("negro");
        colores.add("blanco");
        colores.add("celeste");
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
                        boolean c=false;
                        if (text!="") {
                            buscarColorResultado();
                        }
                        else {
                            actMenuPrincipal.speak(REPETIR_PRONUNCIACION);
                        }
                        stopService(sReconocerVoz);
                        dialogoEscucha.dismiss();
                    }
                },3000);
                dialogoEscucha.show();
            }
        });
    }

    private void buscarColorResultado() {
        for (int i=0;i<palabrasReconocidas.size();i++)
            for (int j=0;j<colores.size();j++)
                if (palabrasReconocidas.get(i).toString().equals(colores.get(j))) {
                    desplegarPosiblesColores(colores.get(j));
                    break;
                }

    }

    private void desplegarPosiblesColores(String color) {
        dialogoColores=new Dialog(this);
        dialogoColores.setTitle("Color: "+color);

        LayoutInflater li=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.dialogo_colores, null, false);
        dialogoColores.setContentView(v);

        ImageButton btnCol1=(ImageButton)dialogoColores.findViewById(R.id.imgBtnCol1);
        ImageButton btnCol2=(ImageButton)dialogoColores.findViewById(R.id.imgBtnCol2);
        ImageButton btnCol3=(ImageButton)dialogoColores.findViewById(R.id.imgBtnCol3);
        ImageButton btnCol4=(ImageButton)dialogoColores.findViewById(R.id.imgBtnCol4);

        if (color.equals("rojo")){
            btnCol1.setBackgroundColor(getResources().getColor(R.color.rojo1));
            btnCol1.setTag(getResources().getString(R.color.rojo1));

            btnCol2.setBackgroundColor(getResources().getColor(R.color.rojo2));
            btnCol2.setTag(getResources().getString(R.color.rojo2));

            btnCol3.setBackgroundColor(getResources().getColor(R.color.rojo3));
            btnCol3.setTag(getResources().getString(R.color.rojo3));

            btnCol4.setBackgroundColor(getResources().getColor(R.color.rojo4));
            btnCol4.setTag(getResources().getString(R.color.rojo4));
        }

        if (color.equals("rosa")||color.equals("rosado")){
            btnCol1.setBackgroundColor(getResources().getColor(R.color.rosado1));
            btnCol1.setTag(getResources().getString(R.color.rosado1));

            btnCol2.setBackgroundColor(getResources().getColor(R.color.rosado2));
            btnCol2.setTag(getResources().getString(R.color.rosado2));

            btnCol3.setBackgroundColor(getResources().getColor(R.color.rosado3));
            btnCol3.setTag(getResources().getString(R.color.rosado3));

            btnCol4.setBackgroundColor(getResources().getColor(R.color.rosado4));
            btnCol4.setTag(getResources().getString(R.color.rosado4));
        }
        if (color.equals("amarillo")){
            btnCol1.setBackgroundColor(getResources().getColor(R.color.amarillo1));
            btnCol1.setTag(getResources().getString(R.color.amarillo1));

            btnCol2.setBackgroundColor(getResources().getColor(R.color.amarillo2));
            btnCol2.setTag(getResources().getString(R.color.amarillo2));

            btnCol3.setBackgroundColor(getResources().getColor(R.color.amarillo3));
            btnCol3.setTag(getResources().getString(R.color.amarillo3));

            btnCol4.setBackgroundColor(getResources().getColor(R.color.amarillo4));
            btnCol4.setTag(getResources().getString(R.color.amarillo4));
        }

        if (color.equals("purpura")||color.equals("violeta")){
            btnCol1.setBackgroundColor(getResources().getColor(R.color.purpura1));
            btnCol1.setTag(getResources().getString(R.color.purpura1));

            btnCol2.setBackgroundColor(getResources().getColor(R.color.purpura2));
            btnCol2.setTag(getResources().getString(R.color.purpura2));

            btnCol3.setBackgroundColor(getResources().getColor(R.color.purpura3));
            btnCol3.setTag(getResources().getString(R.color.purpura3));

            btnCol4.setBackgroundColor(getResources().getColor(R.color.purpura4));
            btnCol4.setTag(getResources().getString(R.color.purpura4));
        }

        if (color.equals("plomo")){
            btnCol1.setBackgroundColor(getResources().getColor(R.color.plomo1));
            btnCol1.setTag(getResources().getString(R.color.plomo1));

            btnCol2.setBackgroundColor(getResources().getColor(R.color.plomo2));
            btnCol2.setTag(getResources().getString(R.color.plomo2));

            btnCol3.setBackgroundColor(getResources().getColor(R.color.plomo3));
            btnCol3.setTag(getResources().getString(R.color.plomo3));

            btnCol4.setBackgroundColor(getResources().getColor(R.color.plomo4));
            btnCol4.setTag(getResources().getString(R.color.plomo4));
        }

        if (color.equals("café")||color.equals("marron")){
            btnCol1.setBackgroundColor(getResources().getColor(R.color.cafe1));
            btnCol1.setTag(getResources().getString(R.color.cafe1));

            btnCol2.setBackgroundColor(getResources().getColor(R.color.cafe2));
            btnCol2.setTag(getResources().getString(R.color.cafe2));

            btnCol3.setBackgroundColor(getResources().getColor(R.color.cafe3));
            btnCol3.setTag(getResources().getString(R.color.cafe3));

            btnCol4.setBackgroundColor(getResources().getColor(R.color.cafe4));
            btnCol4.setTag(getResources().getString(R.color.cafe4));
        }

        if (color.equals("azul")){
            btnCol1.setBackgroundColor(getResources().getColor(R.color.azul1));
            btnCol1.setTag(getResources().getString(R.color.azul1));

            btnCol2.setBackgroundColor(getResources().getColor(R.color.azul2));
            btnCol2.setTag(getResources().getString(R.color.azul2));

            btnCol3.setBackgroundColor(getResources().getColor(R.color.azul3));
            btnCol3.setTag(getResources().getString(R.color.azul3));

            btnCol4.setBackgroundColor(getResources().getColor(R.color.azul4));
            btnCol4.setTag(getResources().getString(R.color.azul4));
        }

        if (color.equals("verde")){
            btnCol1.setBackgroundColor(getResources().getColor(R.color.verde3));
            btnCol1.setTag(getResources().getString(R.color.verde3));

            btnCol2.setBackgroundColor(getResources().getColor(R.color.verde2));
            btnCol2.setTag(getResources().getString(R.color.verde2));

            btnCol3.setBackgroundColor(getResources().getColor(R.color.verde4));
            btnCol3.setTag(getResources().getString(R.color.verde4));

            btnCol4.setBackgroundColor(getResources().getColor(R.color.verde));
            btnCol4.setTag(getResources().getString(R.color.verde));
        }

        if (color.equals("negro")){
            btnCol1.setBackgroundColor(getResources().getColor(R.color.negro));
            btnCol1.setTag(getResources().getString(R.color.negro));

            btnCol2.setBackgroundColor(getResources().getColor(R.color.negro2));
            btnCol2.setTag(getResources().getString(R.color.negro2));

            btnCol3.setBackgroundColor(getResources().getColor(R.color.negro3));
            btnCol3.setTag(getResources().getString(R.color.negro3));

            btnCol4.setBackgroundColor(getResources().getColor(R.color.negro4));
            btnCol4.setTag(getResources().getString(R.color.negro4));
        }

        if (color.equals("blanco")){
            btnCol1.setBackgroundColor(getResources().getColor(R.color.blanco1));
            btnCol1.setTag(getResources().getString(R.color.blanco1));

            btnCol2.setBackgroundColor(getResources().getColor(R.color.blanco2));
            btnCol2.setTag(getResources().getString(R.color.blanco2));

            btnCol3.setBackgroundColor(getResources().getColor(R.color.blanco3));
            btnCol3.setTag(getResources().getString(R.color.blanco3));

            btnCol4.setBackgroundColor(getResources().getColor(R.color.blanco4));
            btnCol4.setTag(getResources().getString(R.color.blanco4));
        }

        if (color.equals("naranja")){
            btnCol1.setBackgroundColor(getResources().getColor(R.color.naranja));
            btnCol1.setTag(getResources().getString(R.color.naranja));

            btnCol2.setBackgroundColor(getResources().getColor(R.color.naranja2));
            btnCol2.setTag(getResources().getString(R.color.naranja2));

            btnCol3.setBackgroundColor(getResources().getColor(R.color.naranja3));
            btnCol3.setTag(getResources().getString(R.color.naranja3));

            btnCol4.setBackgroundColor(getResources().getColor(R.color.naranja4));
            btnCol4.setTag(getResources().getString(R.color.naranja4));
        }

        if (color.equals("celeste")){
            btnCol1.setBackgroundColor(getResources().getColor(R.color.celeste1));
            btnCol1.setTag(getResources().getString(R.color.celeste1));

            btnCol2.setBackgroundColor(getResources().getColor(R.color.celeste2));
            btnCol2.setTag(getResources().getString(R.color.celeste2));

            btnCol3.setBackgroundColor(getResources().getColor(R.color.celeste3));
            btnCol3.setTag(getResources().getString(R.color.celeste3));

            btnCol4.setBackgroundColor(getResources().getColor(R.color.celeste4));
            btnCol4.setTag(getResources().getString(R.color.celeste4));
        }

        dialogoColores.show();
    }

    public void cambioColor(View view){
        dialogoColores.dismiss();
        drawView.setErase(false);
        drawView.setBrushSize(drawView.getLastBrushSize());
        String color = view.getTag().toString();
        drawView.setColor(color);
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
