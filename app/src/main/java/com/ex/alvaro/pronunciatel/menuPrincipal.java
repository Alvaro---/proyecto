package com.ex.alvaro.pronunciatel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

import Clases.Usuario;


public class menuPrincipal extends Activity /*implements TextToSpeech.OnInitListener*/{

    //Context para llamar en cualquier clase
    public static Context con;
    //Elementos de la pantalla
    Button btnActividades, btnPuntuacion, btnAyuda, btnUsuario;
    TextView lblBienvenido;

    //Para armar un saludo con el nombre
    String bienvenido;

    //Frase a leer cuando ingresa un nombre
    String leer1="Hola";

    //servicio de reconocimiento de voz
    Intent service1;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu);
        cargarElementosInterfaz();

        //definir context que sera usado por cualquier clase
        con=getApplicationContext();

        //iniciar servicio
        service1= new Intent(con, reconocerVoz.class);

        //Valores iniciales
        bienvenido=lblBienvenido.getText().toString();

        //Crea un usuario para cargar los valores existentes
        Usuario usu=new Usuario("");
        usu.cargarUsuario();

        //Verifica nombre vacio
        if (usu.getNombre().equals("")){
            /* El handler sirve para esperar unos segundos antes de la llamada al TTS.
            El TTS no puede iniciarse con la aplicacion. Debe llamarse una vez que la aplicacion ya esta iniciada.
             */
            Handler espera =new Handler();
            espera.postDelayed(new Runnable() {
                @Override
                public void run() {
                    leer1="Hola, ¿Cual es tu nombre?";
                    lblBienvenido.callOnClick(); //su metodo leera un saludo.
                }
            }, 3000);
            abrirDialogoIngresoNombre(); //INGRESAR NOMBRE DE USUARIO

        } //SI YA EXISTE UN USUARIO
        else {
            //Continuar al menu
            lblBienvenido.setText(bienvenido+" "+usu.getNombre());
            leer1=lblBienvenido.getText().toString();
        }

        //ACCIONES DE ELEMENTOS
        lblBienvenido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // speak(leer1);
                Intent service = new Intent(con, leerTTS.class);
                service.putExtra("leeme", leer1);
                con.startService(service);
            }
        });
    }

    public void abrirDialogoIngresoNombre() {
        final Dialog dialogIngreso = new Dialog(this);
        dialogIngreso.setTitle("Ingresa tu nombre");
        dialogIngreso.setContentView(R.layout.dialogo_ingreso_nombre);

        ToggleButton btnIngresoNombre =(ToggleButton)dialogIngreso.findViewById(R.id.btnIngresoNombre);
        btnIngresoNombre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //llamar a un service para ingvreso de nombre
                    startService(service1);
                    Toast.makeText(con, "toca el boton", Toast.LENGTH_SHORT).show();

                    service1.putExtra("clase","menuPrincipal");

                    //ArrayList <String> resultados=reconocerVoz.resultados;


                    //dialogIngreso.dismiss();
                }else{
                    stopService(service1);
                }

            }
        });
        dialogIngreso.show();
    }

    public static void resultado(){

    }


    /***********************ELEMENTOS DE INTERFAZ ***************************************
     * ******************************************************************************************
     *******************************************************************/


    private void cargarElementosInterfaz() {
        //Botones
        btnActividades=(Button)findViewById(R.id.btnActividades);
        btnPuntuacion=(Button)findViewById(R.id.btnPuntuacion);
        btnUsuario=(Button)findViewById(R.id.btnUsuario);
        btnAyuda=(Button)findViewById(R.id.btnAyuda);
        //Labels - TextView
        lblBienvenido=(TextView)findViewById(R.id.lblBienvenida);
    }


    /**************   METODOS TTS    ******************************************************************
     ********************************************************************************************
     *************************************************************************************************/
}
