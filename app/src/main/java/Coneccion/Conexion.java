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
    private String tbPreguntas="CREATE TABLE actpreguntas (pregunta TEXT, palabra TEXT, palabra2 TEXT, imagen TEXT)";


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


    //PARA ACTIVIDAD DE PREGUTNAS

    private String actPreg1="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Choco entre dos paredes \nlate mi corazón \nQuien no sepa mi nombre \nes un cabezón.', 'el chocolate', 'chocolate', 'chocolate')";
    private String actPreg2="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Blanca por dentro,\nverde por fuera. \nSi no sabes, \nespera.', 'la pera', 'pera', 'pera2')";
    private String actPreg3="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Un señor gordito,\nmuy coloradito, \nno toma café, \nsiempre toma té', 'el tomate', 'tomate', 'tomate')";
    private String actPreg4="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Oro parece, plata no es. \nAbran las cortinas, \ny verán lo que es.', 'el platano', 'platano', 'platano')";
    private String actPreg5="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Lo come Pancracio, \nestá en el champán; \nsi piensas despacio \nsabrás que es el…', 'el pan', 'pan', 'pan')";
    private String actPreg6="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Por dentro soy carbón,\npor fuera soy madera, \nviajo en tu estuche \ny me llevas a la escuela.', 'el lápiz', 'lápiz', 'lapiz')";
    private String actPreg7="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Me llamo Leo,\nme apellido Pardo,\nquien no me adivine \nes un poco tardo. ', 'el leopardo', 'leopardo', 'leopardo')";
    private String actPreg8="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Llevo mi casita a cuestas camino con una pata \ny voy dejando mi huella \ncon un hilito de plata.', 'el caracol', 'caracol', 'caracol')";
    private String actPreg9="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Un animal que tiene \nojos de gato, \norejas de gato, \npatas de gato, \nrabo de gato \ny no es gato.', 'la gata', 'gata', 'gata')";
    private String actPreg10="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Soy un animal muy elegante, \nmuy veloz y poco fiero; \ny cuando quiero calzarme \nvoy a casa del herrero. ', 'el caballo', 'caballo', 'caballo2')";
    private String actPreg11="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Antes huevecito, \ndespués capullito,\nmás tarde volaré \ncomo un pajarito', 'la mariposa', 'mariposa', 'mariposa')";
    private String actPreg12="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Soy astuto y juguetón\ny cazar un ratón\nes mi mayor afición.', 'el gato', 'gato', 'gato2')";
    private String actPreg13="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Cuál es el animal,\nde campo o corral,\nque si una zanahoria le das\nsus dientecitos verás?', 'el conejo', 'conejo', 'conejo')";
    private String actPreg14="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values(' Vuelo de noche, \nduermo de día \ny nunca verás plumas \nen el ala mía', 'el murciélago', 'murciélago', 'murcielago')";
    private String actPreg15="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Tengo agujas pero no sé coser, \ntengo números pero no sé leer,\nlas horas te doy,\n¿sabes quién soy?', 'el reloj', 'reloj', 'reloj')";
    private String actPreg16="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Listo y grandullón, \nsi le preguntas no te habla.\nPero se sabe todas las respuestas,\nporque tiene todas las palabras.', 'el diccionario', 'diccionario', 'diccionario')";





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
        db.execSQL(tbPreguntas);

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

        //insercion de preguntas
        db.execSQL(actPreg1);
        db.execSQL(actPreg2);
        db.execSQL(actPreg4);
        db.execSQL(actPreg5);
        db.execSQL(actPreg6);
        db.execSQL(actPreg7);
        db.execSQL(actPreg8);
        db.execSQL(actPreg9);
        db.execSQL(actPreg10);
        db.execSQL(actPreg11);
        db.execSQL(actPreg12);
        db.execSQL(actPreg13);
        db.execSQL(actPreg14);
        db.execSQL(actPreg15);
        db.execSQL(actPreg16);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
