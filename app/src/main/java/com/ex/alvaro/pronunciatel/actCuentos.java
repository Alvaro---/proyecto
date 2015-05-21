package com.ex.alvaro.pronunciatel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import Clases.Cuento;

/**
 * Created by Alvaro on 20/05/2015.
 */
public class actCuentos extends Activity implements View.OnClickListener {


    Button btnContinuar, btnOtro;
    TextView lblCuento;

    public static Context con;
    Cuento cuento=new Cuento("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cuentos);
        con=getApplicationContext();
        cargarElementosInterfaz();
        cargarAccionesElementos();
        cargarCuento();

    }

    private void cargarCuento() {
        cuento.cargarCuento();
        lblCuento.setText(cuento.getCuento());
    }

    private void cargarAccionesElementos() {
        lblCuento.setOnClickListener(this);
        btnOtro.setOnClickListener(this);
        btnContinuar.setOnClickListener(this);
    }

    private void cargarElementosInterfaz() {
        lblCuento=(TextView)findViewById(R.id.lblCuento);
        btnOtro=(Button)findViewById(R.id.btnOtroCuento);
        btnContinuar=(Button)findViewById(R.id.btnEstoyListo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOtroCuento:
                cargarCuento();
                break;

            case R.id.btnEstoyListo:
                break;

            case R.id.lblCuento:
                speak(lblCuento.getText().toString());
                break;
        }
    }

    public void speak(String leer1){
        Intent service = new Intent(getApplicationContext(), leerTTS.class);
        service.putExtra("leeme", leer1);
        getApplicationContext().startService(service);
    }
}
