package com.ex.alvaro.pronunciatel;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import Clases.Usuario;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Crea un usuario para cargar los valores existentes
        Usuario usu=new Usuario("",getApplicationContext());
        usu.cargarUsuario();

        //Verifica nombre vacio
        if (usu.getNombre().equals("")){
            //Ingresar Nomber de usuario
        }else {
            //Continuar al menu
        }





    }

}
