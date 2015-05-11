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

import java.util.ArrayList;

import Clases.Usuario;


public class actMenuPrincipal extends Activity /*implements TextToSpeech.OnInitListener*/{

    int cantidadUsuarios=0;

    private String LOG_TAG="Menu Principal";
    //Context para llamar en cualquier clase
    public static Context con;
    //Elementos de la pantalla
    Button btnActividades, btnPuntuacion, btnAyuda, btnUsuario;
    TextView lblBienvenido;
    TextView lblQueHacer;

    //listView de nombres reconocidos
    ListView lista_nombres;

    //Para armar un saludo con el nombre
    String bienvenido;
    //Frases para leer
    String HOLA="Hola";
    String REPETIR_NOMBRE="Puedes repetirlo?";
    String INGRESAR_NOMBRE="como te llamas?, pulsa el botón y dime tu nombre";
    String QUE_HACER="Que quieres hacer";
    String ACTIVIDADES="Veamos las actividades";
    String PUNTUACIONES="veamos las puntuaciones";
    String CAMBIAR_NOMBRE="Veamos si tu nombre esta aqui";
    String SELECCIONA_NOMBRE="Cual de estoss es tu nombre?";
    String EL_NOMBRE_YA_EXISTE="Ese nombre ya existe, seleccionalo";
    String ELIMINADO_CORRECTAMENTE="Se elimino el nombre de ";

    String NOBMRE_MANUAL="Escribe tu nombre en el cuadro";

    String NINGUNO="NINGUNO DE ESTOS ES MI NOMBRE";

    //servicio de reconocimiento de voz
    Intent sReconocerVoz;
    //Almacen de nombres
    ArrayList<String> nombres_reconocidos;
    ArrayList<String> nombres_existentes=new ArrayList<String>();

    String text; // TEXTO DE NOMBRES RECONOCIDOS - TEXTORECONOCIDO

    //contador de intentos antes de que permita escribir el nombre con el teclado
    int c=0;
    int c2=0;

    //NOMBRE DE USUARIO DE SESION
    static Usuario usuario=new Usuario("");

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

        btnPuntuacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(PUNTUACIONES);
                Intent intentPuntuaciones=new Intent(actMenuPrincipal.this, actPuntuaciones.class);
                startActivity(intentPuntuaciones);
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

        lista_nombres.setLongClickable(true);
        lista_nombres.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                final Dialog dialogoEliminarModificar = new Dialog(actMenuPrincipal.this);
                dialogoEliminarModificar.setTitle("Editar o eliminar un nombre");

                LayoutInflater li=(LayoutInflater)actMenuPrincipal.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = li.inflate(R.layout.dialogo_eliminar_modificar, null, false);
                dialogoEliminarModificar.setContentView(v);
                //speak(SELECCIONA_NOMBRE);

                Button btnEliminar=(Button)dialogoEliminarModificar.findViewById(R.id.btnEliminarNombre);
                Button btnModificar=(Button)dialogoEliminarModificar.findViewById(R.id.btnModificarNombre);

                btnEliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Usuario usu =new Usuario((String)parent.getItemAtPosition(position));
                        Toast.makeText(con,(String)parent.getItemAtPosition(position),Toast.LENGTH_LONG);

                        usu.EliminarNombre();
                        dialogoEliminarModificar.dismiss();
                        speak(ELIMINADO_CORRECTAMENTE+" "+usu.getNombre());
                        dialogSeleccion.dismiss();


                    }
                });

                btnModificar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        abrirDialogoIngresoManual((String)parent.getItemAtPosition(position));
                        dialogoEliminarModificar.dismiss();
                        dialogSeleccion.dismiss();
                    }
                });
                dialogoEliminarModificar.show();
                return true;
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
        btnIngresoNombre.setTextOn("Habla");
        btnIngresoNombre.setTextOff("Pulsame");
        btnIngresoNombre.setText("Pulsame");
        btnIngresoNombre.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //llamar a un service para ingreso de nombre en un metodo..
                    startService(sReconocerVoz);
                    // service1.putExtra("clase","menuPrincipal");
                    espera.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnIngresoNombre.setChecked(false);
                            text = reconocerVoz.getTexto();
                            nombres_reconocidos=reconocerVoz.getResultados();
                            if(verificarResultados()) {
                                dialogIngreso.dismiss();
                                c2=0;
                            }else {
                                if (c2==2){
                                    c2=0;
                                    abrirDialogoIngresoManual("");
                                    dialogIngreso.dismiss();
                                }
                                speak(REPETIR_NOMBRE);
                                c2++;
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

    public boolean verificarResultados(){
        Log.d(LOG_TAG, text);
        if (text!=""){
            //abrir dialogo seleccion de nombre
            abrirDialogoSeleccionNombre();
            text="";
            nombres_reconocidos=null;
            return true;
        }
        else
            return false;
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
                    }else{
                        Toast.makeText(con,EL_NOMBRE_YA_EXISTE,Toast.LENGTH_LONG).show();
                        speak(EL_NOMBRE_YA_EXISTE);
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
        },1500);
    }

    private void abrirDialogoIngresoManual(String nombreA) {
        final Dialog dialogIngresoManual = new Dialog(this);
        final String nombreAnterior=nombreA;
        dialogIngresoManual.setTitle("Ingrea tu nombre manualmente");
        LayoutInflater li=(LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.dialogo_ingreso_nombre_manual, null, false);
        dialogIngresoManual.setContentView(v);

        speak(NOBMRE_MANUAL);

        final EditText nombre_manual=(EditText) dialogIngresoManual.findViewById(R.id.txtNombreManual);
        final Button btnAceptar=(Button)dialogIngresoManual.findViewById(R.id.btnAceptarNombreManual);
        final Button btnCancelar=(Button)dialogIngresoManual.findViewById(R.id.btnCancelarNombreManual);

        nombre_manual.setText(nombreA);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre=nombre_manual.getText().toString();
                usuario.setNombre(nombre);
                if (nombreAnterior.equals("")){
                    if(usuario.guardarNuevoUsuario()){
                        saludar(nombre);
                        lblBienvenido.setText(bienvenido+" "+usuario.getNombre().toUpperCase());
                        dialogIngresoManual.dismiss();
                    }else{
                        Toast.makeText(con,EL_NOMBRE_YA_EXISTE,Toast.LENGTH_LONG).show();
                        saludar(nombre);
                        dialogIngresoManual.dismiss();
                    }
                }else{
                    if(usuario.modificarUsuario(nombreAnterior)){
                        saludar(nombre);
                        lblBienvenido.setText(bienvenido+" "+nombre);
                        dialogIngresoManual.dismiss();
                    }
                }

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
