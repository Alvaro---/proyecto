package com.ex.alvaro.pronunciatel;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Alvaro on 13/04/2015.
 */
public class actPintar extends Activity implements View.OnClickListener{

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
    String CAMBIOCOLOR="Pronuncia un color";
    String REPETIR_PRONUNCIACION="¿Puedes repetirlo?";
    String ADVERTENCIA_NUEVO="¿Quieres comenzar un nuevo dibujo?. El dibujo actual se borrara";
    String NO_ENCONTRADO="No entendí bien el color. ¿Puedes repetirlo?";
    String INSTRUCCION_NOMBRE_DIBUJO="Pronuncia el nombre del dibujo";

    //colores a reconocerse
    ArrayList<String> colores = new ArrayList<String>();

    Dialog dialogoColores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pintar);
        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);
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
                actMenuPrincipal.speak(CAMBIOCOLOR);
                espera.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startService(sReconocerVoz);
                    }
                },1000);
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
                },4000);
                dialogoEscucha.show();
            }
        });

        btnPincel.setOnClickListener(this);

        btnBorrar.setOnClickListener(this);

        btnGuardar.setOnClickListener(this);

        btnNuevo.setOnClickListener(this);

    }

    private void buscarColorResultado() {
        boolean encontrado=false;
        for (int i=0;i<palabrasReconocidas.size();i++)
            for (int j=0;j<colores.size();j++)
                if (palabrasReconocidas.get(i).toString().equals(colores.get(j))) {
                    encontrado=true;
                    desplegarPosiblesColores(colores.get(j));
                    break;
                }
        if (!encontrado)
            actMenuPrincipal.speak(NO_ENCONTRADO);
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

        if (color.equals("purpura")||color.equals("violeta")||color.equals("morado")||color.equals("lila")){
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

        if (color.equals("café")||color.equals("marron")||color.equals("cafe")){
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

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.brush_btn) {
            final Dialog brushDialog = new Dialog(con);
            brushDialog.setTitle("Tamañao de paleta");
            brushDialog.setContentView(R.layout.brush_chooser);
            //ACCIONES PARA CADA UNO DE LOS BOTONES DEL MENU DE BROCHAS BRUSH DIALOG

            ImageButton smallBtn = (ImageButton) brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(smallBrush);
                    drawView.setLastBrushSize(smallBrush);
                    drawView.setErase(false);
                    brushDialog.dismiss();
                }
            });

            ImageButton mediumBtn = (ImageButton) brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(mediumBrush);
                    drawView.setLastBrushSize(mediumBrush);
                    drawView.setErase(false);
                    brushDialog.dismiss();
                }
            });

            ImageButton largeBtn = (ImageButton) brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(largeBrush);
                    drawView.setLastBrushSize(largeBrush);
                    drawView.setErase(false);
                    brushDialog.dismiss();
                }
            });
            brushDialog.show();
        }

        else if (v.getId()==R.id.new_btn) {
            AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
            newDialog.setTitle("Dibujo Nuevo");
            newDialog.setMessage(ADVERTENCIA_NUEVO);
            actMenuPrincipal.speak(ADVERTENCIA_NUEVO);
            newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    drawView.startNew();
                    dialog.dismiss();
                }
            });
            newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            newDialog.show();
        }

        else if (v.getId()==R.id.erase_btn) {
            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle("Eraser size:");
            brushDialog.setContentView(R.layout.brush_chooser);

            ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setErase(true);
                    drawView.setBrushSize(smallBrush);
                    brushDialog.dismiss();
                }
            });
            ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setErase(true);
                    drawView.setBrushSize(mediumBrush);
                    brushDialog.dismiss();
                }
            });
            ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setErase(true);
                    drawView.setBrushSize(largeBrush);
                    brushDialog.dismiss();
                }
            });

            brushDialog.show();
        }

        else if(v.getId()==R.id.save_btn){
            AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
            saveDialog.setTitle("Guardar Dibujo");
            saveDialog.setMessage("¿Quieres guardar el dibujo a la galeria?");
            saveDialog.setPositiveButton("Si", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    //actMenuPrincipal.speak(INSTRUCCION_NOMBRE_DIBUJO);
                    final Dialog dialogoEscucha=new Dialog(con);
                    dialogoEscucha.setTitle(INSTRUCCION_NOMBRE_DIBUJO);
                    dialogoEscucha.setContentView(R.layout.dialogo_escuchando);

                    //espera.postDelayed(new Runnable() {
                      //  @Override
                        //public void run() {
                            startService(sReconocerVoz);
                        //}
                    //},2100);
                    espera.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            text = reconocerVoz.getTexto();
                            palabrasReconocidas = reconocerVoz.getResultados();
                            Log.d("NOMBRES DE DIBUJO: ", text+"*************");
                            if (text != "")
                                guardarDibujo(palabrasReconocidas.get(0));
                            else
                                actMenuPrincipal.speak(REPETIR_PRONUNCIACION);
                            stopService(sReconocerVoz);
                            dialogoEscucha.dismiss();

                        }
                    },3000);
                    dialogoEscucha.show();
                }
            });
            saveDialog.setNegativeButton("No", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            saveDialog.show();
        }

    }

    private void guardarDibujo(String nombre) {
        //HABILITAR CACHE DE GUARDADO
        drawView.setDrawingCacheEnabled(true);
        String imgSaved = MediaStore.Images.Media.insertImage(
                getContentResolver(), drawView.getDrawingCache(),
                nombre+".png", "drawing");
        if(imgSaved!=null){
            Toast savedToast = Toast.makeText(getApplicationContext(),
                    "Dibujo guardado a la galeria", Toast.LENGTH_SHORT);
            savedToast.show();
        }
        else{
            Toast unsavedToast = Toast.makeText(getApplicationContext(),
                    "Ups.. No pude guardar el dibujo. Intentalo de nuevo.", Toast.LENGTH_SHORT);
            unsavedToast.show();
        }

        //DESTRUIR EL CACHE DE GUARDADO
        drawView.destroyDrawingCache();
    }
}
