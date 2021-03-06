package com.ex.alvaro.pronunciatel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import Clases.Usuario;


public class MainActivity extends Activity implements TextToSpeech.OnInitListener{

    //Elementos de la pantalla
    Button btnActividades, btnPuntuacion, btnAyuda, btnUsuario;
    TextView lblBienvenido;

    //Para armar un sludo con el nombre
    String bienvenido;

    //Para leer palabras.
    private TextToSpeech myTTS;
    private int MY_DATA_CHECK_CODE=0;

    //Frase a leer cuando ingresa un nombre
    String leer1="Hola";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarElementosInterfaz();

        //Inicia intent de habla
        Intent checkTTSIntent=new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);

        //Valores iniciales
        bienvenido=lblBienvenido.getText().toString();

        //Crea un usuario para cargar los valores existentes
        Usuario usu=new Usuario("",getApplicationContext());
        usu.cargarUsuario();

        //Verifica nombre vacio
        if (usu.getNombre().equals("")){

            /* El handler sirve para esperar unos segundos antes de la llamada al TTS. El TTS no puede iniciarse con la aplicacion.
            Debe llamarse una vez que la aplicacion ya esta iniciada, como con una accion o alguna forma similar.
            En este caso, la accion no se puede llamar al iniciarse la aplicacion, sino cuando esta ya inicio.
             */
            Handler espera =new Handler();
            espera.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //El label que s leera cambia por la pregunta del nombre
                    leer1="Hola, ¿Cual es tu nombre?";
                    lblBienvenido.callOnClick();
                    //lamar a ventana para introducir texto
                }
            }, 5000);

            //Ingresar Nomber de usuario

        }else {
            //Continuar al menu
            lblBienvenido.setText(bienvenido+" "+usu.getNombre());
            leer1=lblBienvenido.getText().toString();
        }


        //ACCIONES DE ELEMENTOS
        lblBienvenido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(leer1);
            }
        });
    }

    private void cargarElementosInterfaz() {
        //Botones
        btnActividades=(Button)findViewById(R.id.btnActividades);
        btnPuntuacion=(Button)findViewById(R.id.btnPuntuacion);
        btnUsuario=(Button)findViewById(R.id.btnUsuario);
        btnAyuda=(Button)findViewById(R.id.btnAyuda);
        //Labels - TextView
        lblBienvenido=(TextView)findViewById(R.id.lblBienvenida);
    }

    private void speak(String leer) {
        myTTS.speak(leer, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onInit(int status) {
        if (status==TextToSpeech.SUCCESS){
            Locale loc = new Locale ("spa", "ESP");
            myTTS.setLanguage(loc);
        }else if (status==TextToSpeech.ERROR){
            Toast.makeText(this, "Error.. ", Toast.LENGTH_LONG).show();
        }
    }

    protected void onActivityResult (int request_code, int result_code, Intent data){
        if (request_code==MY_DATA_CHECK_CODE){
            if (result_code==TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
                myTTS=new TextToSpeech(this,this);
            }else{
                Intent installTTSIntent=new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }
    }
}
