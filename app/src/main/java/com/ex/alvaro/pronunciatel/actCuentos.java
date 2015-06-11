package com.ex.alvaro.pronunciatel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import Clases.Cuento;

/**
 * Created by Alvaro on 20/05/2015.
 */
public class actCuentos extends Activity implements View.OnClickListener {

    Button btnContinuar, btnOtro;
    TextView lblCuento;
    ImageButton btnAtras;

    TextView lblInstruccionCuentos;

    public static Context con;
    static Cuento cuento=new Cuento("");

    String INSTRUCCION="Escucha, o lee el cuento, y pulsa el boton para ver las preguntas";

    boolean leerInstruccion=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cuentos);
        con=getApplicationContext();
        cargarElementosInterfaz();
        cargarAccionesElementos();
        cargarCuento();
        Bundle b = getIntent().getExtras();
        try{
            leerInstruccion=b.getBoolean("leer");
        }catch(Exception e){
            e.printStackTrace();
        }
        if (leerInstruccion){
            speak(INSTRUCCION);
            leerInstruccion=false;
        }

    }

    private void cargarCuento() {
        cuento.cargarCuento();
        lblCuento.setText(cuento.getCuento());
    }

    private void cargarAccionesElementos() {
        lblCuento.setOnClickListener(this);
        btnOtro.setOnClickListener(this);
        btnContinuar.setOnClickListener(this);
        btnAtras.setOnClickListener(this);
        lblInstruccionCuentos.setOnClickListener(this);
    }

    private void cargarElementosInterfaz() {
        lblCuento=(TextView)findViewById(R.id.lblCuento);
        btnOtro=(Button)findViewById(R.id.btnOtroCuento);
        btnContinuar=(Button)findViewById(R.id.btnEstoyListo);
        btnAtras=(ImageButton)findViewById(R.id.btnAtrasCuentos);
        lblInstruccionCuentos=(TextView)findViewById(R.id.lblInstruccionCuentos);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOtroCuento:
                cargarCuento();
                break;

            case R.id.btnEstoyListo:
                Intent actividad =new Intent(actCuentos.this, actPreguntasCuentos.class);
                startActivity(actividad);
                break;

            case R.id.lblCuento:
                speak(lblCuento.getText().toString());
                break;

            case R.id.btnAtrasCuentos:
                Intent intent= new Intent(actCuentos.this, actMenuActividades.class);
                startActivity(intent);
                break;
            case R.id.lblInstruccionCuentos:
                speak(INSTRUCCION);
                break;
        }
    }

    public void speak(String leer1){
        Intent service = new Intent(getApplicationContext(), leerTTS.class);
        service.putExtra("leeme", leer1);
        getApplicationContext().startService(service);
    }
}
