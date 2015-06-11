package com.ex.alvaro.pronunciatel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by HP on 31/05/2015.
 */
public class actMenuCadenaFonematica extends Activity implements View.OnClickListener{

    Button btnVocales, btnConsonantes, btnCombinadas, btnDiptongos, btnTodas;
    Intent seleccionaTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_cadena_fonematica1);
        cargarElementos();
        btnVocales.setOnClickListener(this);
        btnConsonantes.setOnClickListener(this);
        btnCombinadas.setOnClickListener(this);
        btnDiptongos.setOnClickListener(this);
        seleccionaTipo=new Intent(actMenuCadenaFonematica.this, actSleccionaLetra.class);

    }

    private void cargarElementos() {
        btnVocales=(Button)findViewById(R.id.btnVocales);
        btnConsonantes=(Button)findViewById(R.id.btnConsonantes);
        btnCombinadas=(Button)findViewById(R.id.btnCombinadas);
        btnDiptongos=(Button)findViewById(R.id.btnDiptongos);
        btnTodas=(Button)findViewById(R.id.btnTodas);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnVocales:
                seleccionaTipo.putExtra("select", 1);
                startActivity(seleccionaTipo);
                break;
            case R.id.btnConsonantes:
                seleccionaTipo.putExtra("select", 2);
                startActivity(seleccionaTipo);
                break;
            case R.id.btnCombinadas:
                seleccionaTipo.putExtra("select", 3);
                startActivity(seleccionaTipo);
                break;
            case R.id.btnDiptongos:
                seleccionaTipo.putExtra("select", 4);
                startActivity(seleccionaTipo);
                break;
            case R.id.btnTodas:
                seleccionaTipo.putExtra("select", 5);
                startActivity(seleccionaTipo);
                break;
        }
    }
}
