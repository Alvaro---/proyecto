package Coneccion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alvaro on 04/03/2015.
 */
public class Conexion extends SQLiteOpenHelper {

    //Instancia unica para el patron singleton
    private static Conexion instancia=null;

    //Nombre y version de base de datos.
    private static final String DATABASE_NAME = "TEL";
    private static final int DATABASE_VERSION=1;

    //La tabla de usuarios, contara con un nombre de usuario. Las puntuacioes se asociaran despues a un usuario, por dia.
    private String tbUsuario="CREATE TABLE Usuario (nombre TEXT PRIMARY KEY)";
    private String tbPalabras="CREATE TABLE actimagen (Palabra TEXT PRIMARY KEY, palabra2 TEXT,palabra3 TEXT, palabra4 TEXT, imagen TEXT, descripcion TEXT)";


    //Palabras de ACTIVIDAD IMAGEN
    private String actImg1="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('abuela', 'abuelita', '', '', 'abuela', 'la abuela nos quiere mucho')";
    private String actImg2="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('abuelo', 'abuelito', '', '', 'abuelo', 'el abuelo nos cuenta muchas historias')";
    private String actImg3="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('árbol', 'arbolito', '', '', 'arbol', 'el árbol de frutos')";
    private String actImg4="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('auto', 'autito', '', '', 'auto', 'un auto viaja muy rápido')";
    private String actImg5="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('avión', 'avioncito', '', '', 'avion', 'el avión vuela por todo el mundo')";
    private String actImg6="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('barco', 'barquito', '', '', 'barco', 'un barco sobre el mar')";
    private String actImg7="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('caballo', 'caballito', '', '', 'caballo', 'los caballos son muy rápidos')";
    private String actImg8="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('casa', 'casita', '', '', 'casa', 'una casa muy linda')";
    private String actImg9="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('cebra', '', '', '', 'cebra', 'la cebra tiene rayas negras')";
    private String actImg10="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('dinosaurio', 'dinosaurito', '', '', 'dinosaurio', 'los dinosaurios se extinguieron hace mucho')";
    private String actImg11="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('dragón', 'dragoncito', '', '', 'dragon', 'los dragones lanzan fuego')";
    private String actImg12="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('gato', 'gatito', '', '', 'gato', 'el gato maúlla')";
    private String actImg13="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('guerrero', '', '', '', 'guerrero', 'el guerrero defiende a las personas')";
    private String actImg14="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('guitarra', 'guitarra', '', '', 'guitarra', 'la guitarra produce música muy linda')";
    private String actImg15="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('jirafa', 'jirafa', '', '', 'jirafa', 'la jirafa tiene el cuello muy largo')";
    private String actImg16="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('ladrón', 'ratero', '', '', 'ladron', 'los ladrones son peligrosos')";
    private String actImg17="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('león', 'leoncito', '', '', 'leon', 'el león es el rey de la selva')";
    private String actImg18="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('libro', 'librito', '', '', 'libro', 'los libros tienen muchas historias')";
    private String actImg19="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('luna', 'luna y estrellas', '', '', 'luna', 'la luna sale de noche rodeada de estrellas')";
    private String actImg20="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('manzana', '', '', '', 'manzana', 'la manzana es muy nutritiva')";
    private String actImg21="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('oso', 'osito', 'peluche', '', 'oso', 'el oso de peluche es muy suave')";
    private String actImg22="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('payaso', 'payasito', '', '', 'payaso', 'el payaso nos hace reír')";
    private String actImg23="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('pelota', 'balón', '', '', 'pelota', 'con la pelota podemos jugar muchas cosas')";
    private String actImg24="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('pera', '', '', '', 'pera', 'la pera es una fruta ')";
    private String actImg25="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('perro', 'perrito', '', '', 'perro', 'el perro es un buen amigo')";
    private String actImg26="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('piano', 'teclado', '', '', 'piano', 'el piano toca buenas melodías')";
    private String actImg27="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('planeta', 'tierra', '', '', 'planeta', 'todos vivimos en el planeta tierra')";
    private String actImg28="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('rana', 'sapo', 'ranita', 'sapito', 'rana', 'la rana salta muy alto')";
    private String actImg29="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('ratón', 'ratoncito', '', '', 'raton', 'los ratones son pequeños')";
    private String actImg30="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('regalo', 'paquete', '', '', 'regalo', 'un regalo misterioso es muy divertido')";
    private String actImg31="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('robot', 'robotito', '', '', 'robot', 'los robots pueden hacer muchas cosas')";
    private String actImg32="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('soldado', '', '', '', 'soldado', 'el soldado vive por la patria')";
    private String actImg33="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('tigre', '', '', '', 'tigre', 'el tigre vive en la selva')";
    private String actImg34="INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('uvas', 'uvitas', '', '', 'uvas', 'las uvas crecen en los viñedos')";




    //Para la instanciar la coneccion a SQLite una vez, se utilizara el patron Singleton
    public static Conexion getInstance(Context con) {
        //Verificar si ya existe una instancia
        if (instancia == null) {
            instancia = new Conexion(con.getApplicationContext());
        }
        return instancia;
    }

    //llamada al constructor
    private Conexion(Context context) {
        /*En lugar de esto creo que se podria aumentar todos los campos en el getInstance y ademas en la creacion de la
        instancia dentro del getInstance
        ademas. Eso tal vez para cambiar la version o algo asi.*/
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creacion de tablas
        db.execSQL(tbUsuario);
        db.execSQL(tbPalabras);

        //insercion de palabras actImagenes
        db.execSQL( actImg1);
        db.execSQL( actImg2);
        db.execSQL( actImg3);
        db.execSQL( actImg4);
        db.execSQL( actImg5);
        db.execSQL( actImg6);
        db.execSQL( actImg7);
        db.execSQL( actImg8);
        db.execSQL( actImg9);
        db.execSQL( actImg10);
        db.execSQL( actImg11);
        db.execSQL( actImg12);
        db.execSQL( actImg13);
        db.execSQL( actImg14);
        db.execSQL( actImg15);
        db.execSQL( actImg16);
        db.execSQL( actImg17);
        db.execSQL( actImg18);
        db.execSQL( actImg19);
        db.execSQL( actImg20);
        db.execSQL( actImg21);
        db.execSQL( actImg22);
        db.execSQL( actImg23);
        db.execSQL( actImg24);
        db.execSQL( actImg25);
        db.execSQL( actImg26);
        db.execSQL( actImg27);
        db.execSQL( actImg28);
        db.execSQL( actImg29);
        db.execSQL( actImg30);
        db.execSQL( actImg31);
        db.execSQL( actImg32);
        db.execSQL( actImg33);
        db.execSQL( actImg34);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
