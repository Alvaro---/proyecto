package com.ex.alvaro.pronunciatel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.StringBufferInputStream;
import java.util.ArrayList;

import Clases.Usuario;


public class actMenuPrincipal extends Activity /*implements TextToSpeech.OnInitListener*/{

    private String LOG_TAG="Menu Principal";
    //Context para llamar en cualquier clase
    public static Context con;
    //Elementos de la pantalla
    Button btnActividades, btnPuntuacion, btnAyuda, btnUsuario;
    TextView lblBienvenido;
    TextView lblQueHacer;

    //Para armar un saludo con el nombre
    String bienvenido;

    //Frase a leer cuando ingresa un nombre
    String HOLA="Hola";
    String REPETIR_NOMBRE="Puedes repetirlo?";
    String INGRESAR_NOMBRE="como te llamas?, pulsa el botón y dime tu nombre";
    String QUE_HACER="Que quieres hacer";
    String ACTIVIDADES="Veamos las actividades";
    String PUNTUACIONES="veamos las puntuaciones";
    String CAMBIAR_NOMBRE="Veamos si tu nombre esta aqui";
    String SELECCIONA_NOMBRE="Cual de estoss es tu nombre?";
    String NOBMRE_MANUAL="Escribe tu nombre en el cuadro";

    String NINGUNO="Ninguno de estos es mi nombre";

    //servicio de reconocimiento de voz
    Intent sReconocerVoz;

    //Almacen de nombres
    ArrayList<String> nombres_reconocidos;
    ArrayList<String> nombres_existentes;

    //listView de nombres reconocidos
    ListView lista_nombres;

    //contador de intentos antes de que permita escribir el nombre con el teclado
    int c=0;

    //NOMBRE DE USUARIO DE SESION
    Usuario usuario=new Usuario("");

    Handler espera =new Handler();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu);
        cargarElementosInterfaz();

        //definir context que sera usado por cualquier clase
        con=getApplicationContext();

        //iniciar servicio
        sReconocerVoz= new Intent(con, reconocerVoz.class);

        //Valores iniciales
        bienvenido=lblBienvenido.getText().toString();

        //Crea un usuario para cargar los valores existentes
        usuario.cargarUsuario();

        //Verifica nombre vacio
        if (usuario.getNombre().equals("")){
            /* El handler sirve para esperar unos segundos antes de la llamada al TTS.
            El TTS no puede iniciarse con la aplicacion. Debe llamarse una vez que la aplicacion ya esta iniciada.
             */

            espera.postDelayed(new Runnable() {
                @Override
                public void run() {
                    speak(HOLA); //su metodo leera un saludo.
                }
            }, 1000);

            espera.postDelayed(new Runnable() {
                @Override
                public void run() {
                    abrirDialogoIngresoNombre(); //INGRESAR NOMBRE DE USUARIO
                }
            },2000);

        }
        else {//SI YA EXISTE UN USUARIO
            //Continuar al menu
            lblBienvenido.setText(bienvenido+" "+usuario.getNombre().toUpperCase());
            saludar(usuario.getNombre());

        }

        crearActiones();
    }

    private void crearActiones() {
        //ACCIONES DE ELEMENTOS
        //Label de titulo. Reproduce Sonido. Se llama al iniciar por primera vez
        lblBienvenido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(bienvenido);
            }
        });
        //Label de pregunta de que hacer
        lblQueHacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(QUE_HACER);
            }
        });

        btnActividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(ACTIVIDADES);
                Intent intentActividades=new Intent(actMenuPrincipal.this, actMenuActividades.class );
                startActivity(intentActividades);
            }
        });

        btnUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambioUsuario();
            }
        });
    }

    private void cambioUsuario() {
        speak(SELECCIONA_NOMBRE);

        final Dialog dialogSeleccion = new Dialog(this);
        dialogSeleccion.setTitle("Selecciona el nombre correcto");

        //listview para dialogocoderzheaven - android dialog with listview
        LayoutInflater li=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.dialogo_seleccion_nombre, null, false);

        dialogSeleccion.setContentView(v);
        speak(SELECCIONA_NOMBRE);
        lista_nombres=(ListView) dialogSeleccion.findViewById(R.id.word_list);

        nombres_existentes=usuario.cargarTodosNombre();
        nombres_existentes.add(NINGUNO);

        lista_nombres.setAdapter(new ArrayAdapter<String>(dialogSeleccion.getContext(), R.layout.word, nombres_existentes));

        lista_nombres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombre= (String)parent.getItemAtPosition(position);
                if (nombre.equals(NINGUNO)){
                    abrirDialogoIngresoNombre();
                    dialogSeleccion.dismiss();
                }else{
                    usuario.setNombre(nombre);
                    lblBienvenido.setText(bienvenido+" "+usuario.getNombre());
                    saludar(nombre);
                    dialogSeleccion.dismiss();
                }
            }
        });

        dialogSeleccion.show();

    }

    public static void speak(String leer1){
        Intent service = new Intent(con, leerTTS.class);
        service.putExtra("leeme", leer1);
        con.startService(service);
    }

    public void abrirDialogoIngresoNombre() {
        final Dialog dialogIngreso = new Dialog(this);
        dialogIngreso.setTitle("Ingresa tu nombre");
        dialogIngreso.setContentView(R.layout.dialogo_ingreso_nombre);
        speak(INGRESAR_NOMBRE);
        final ToggleButton btnIngresoNombre =(ToggleButton)dialogIngreso.findViewById(R.id.btnIngresoNombre);
        btnIngresoNombre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //llamar a un service para ingreso de nombre en un metodo..
                    startService(sReconocerVoz);
                    //Toast.makeText(con, "toca el boton", Toast.LENGTH_SHORT).show();
                    // service1.putExtra("clase","menuPrincipal");
                    //Log.v(LOG_TAG, "Llamar al servicio reconocimiento (Despues)");

                    espera.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnIngresoNombre.setChecked(false);
                            String text = reconocerVoz.getTexto();
                            nombres_reconocidos=reconocerVoz.getResultados();
                            Log.d(LOG_TAG, text);
                            if (text!=""){
                                dialogIngreso.dismiss();
                                //abrir dialogo seleccion de nombre
                                abrirDialogoSeleccionNombre();
                            }
                            else {
                                speak(REPETIR_NOMBRE);
                            }
                        }
                    }, 3500);
                }else{
                    stopService(sReconocerVoz);
                }
            }
        });
        dialogIngreso.show();
    }

    public void abrirDialogoSeleccionNombre(){
        final Dialog dialogSeleccion = new Dialog(this);
        dialogSeleccion.setTitle("Selecciona el nombre correcto");

        //listview para dialogocoderzheaven - android dialog with listview
        LayoutInflater li=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.dialogo_seleccion_nombre, null, false);

        dialogSeleccion.setContentView(v);
        speak(SELECCIONA_NOMBRE);
        lista_nombres=(ListView) dialogSeleccion.findViewById(R.id.word_list);

        nombres_reconocidos.add(NINGUNO);

        lista_nombres.setAdapter(new ArrayAdapter<String>(dialogSeleccion.getContext(), R.layout.word, nombres_reconocidos));

        for (int i=0;i<nombres_reconocidos.size();i++){
            nombres_reconocidos.set(i,nombres_reconocidos.get(i).toString().toUpperCase());
        }

        lista_nombres.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombre= (String)parent.getItemAtPosition(position);
                if (nombre.equals(NINGUNO)){
                    dialogSeleccion.dismiss();
                    c++;
                    if (c<3)
                        abrirDialogoIngresoNombre();
                    else
                        abrirDialogoIngresoManual("");
                }else{
                    c=0;
                    usuario.setNombre(nombre);
                    if(usuario.guardarNuevoUsuario()){
                        lblBienvenido.setText(bienvenido+" "+usuario.getNombre());
                        saludar(nombre);
                    }
                    dialogSeleccion.dismiss();
                }
            }
        });

        dialogSeleccion.show();

    }

    public void saludar(String nombre){
        speak(HOLA + " " + nombre);
        espera.postDelayed(new Runnable() {
            @Override
            public void run() {
                speak(QUE_HACER);
            }
        },1000);
    }

    private void abrirDialogoIngresoManual(String nombre) {
        final Dialog dialogIngresoManual = new Dialog(this);
        dialogIngresoManual.setTitle("Ingrea tu nobmre manualmente");
        LayoutInflater li=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.dialogo_ingreso_nombre_manual, null, false);
        dialogIngresoManual.setContentView(v);

        speak(NOBMRE_MANUAL);

        final EditText nombre_manual=(EditText) dialogIngresoManual.findViewById(R.id.txtNombreManual);
        final Button btnAceptar=(Button)dialogIngresoManual.findViewById(R.id.btnAceptarNombreManual);
        final Button btnCancelar=(Button)dialogIngresoManual.findViewById(R.id.btnCancelarNombreManual);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre=nombre_manual.getText().toString();
                usuario.setNombre(nombre);
                usuario.guardarNuevoUsuario();
                saludar(nombre);
                dialogIngresoManual.dismiss();

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogIngresoManual.dismiss();
            }
        });

        dialogIngresoManual.show();
    }

    /***********************ELEMENTOS DE INTERFAZ ***************************************
     *******************************************************************/

    private void cargarElementosInterfaz() {
        //Botones
        btnActividades=(Button)findViewById(R.id.btnActividades);
        btnPuntuacion=(Button)findViewById(R.id.btnPuntuacion);
        btnUsuario=(Button)findViewById(R.id.btnUsuario);
        btnAyuda=(Button)findViewById(R.id.btnAyuda);
        //Labels - TextView
        lblBienvenido=(TextView)findViewById(R.id.lblBienvenida);
        lblQueHacer=(TextView)findViewById(R.id.lblQueDeseaHacer);
    }
}
