package com.ex.alvaro.pronunciatel;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import Clases.Usuario;


public class MainActivity extends Activity {

    //Elementos de la pantalla
    Button btnActividades, btnPuntuacion, btnAyuda, btnUsuario;
    TextView lblBienvenido;

    String bienvenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarElementosInterfaz();
        bienvenido=lblBienvenido.getText().toString();

        //Crea un usuario para cargar los valores existentes
        Usuario usu=new Usuario("",getApplicationContext());
        usu.cargarUsuario();

        //Verifica nombre vacio
        if (usu.getNombre().equals("")){
            //Ingresar Nomber de usuario
        }else {
            //Continuar al menu
            lblBienvenido.setText(bienvenido+" "+usu.getNombre());
        }
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

}
