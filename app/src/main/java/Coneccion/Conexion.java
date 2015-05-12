package Coneccion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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
    private String tbUsuario="CREATE TABLE Usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT UNIQUE)";
    private String tbPalabras="CREATE TABLE actimagen (id INTEGER PRIMARY KEY AUTOINCREMENT, Palabra TEXT, palabra2 TEXT, palabra3 TEXT, palabra4 TEXT, imagen TEXT, descripcion TEXT)";
    private String tbPreguntas="CREATE TABLE actpreguntas (id INTEGER PRIMARY KEY AUTOINCREMENT, pregunta TEXT, palabra TEXT, palabra2 TEXT, imagen TEXT)";
    private String tbPuntuacionPalabras="CREATE TABLE puntuacionImagen (fecha TEXT not null, hora TEXT not null,puntuacion NUMERIC, idUsuario INTEGER, idPalabra INTEGER, palabraPronunciada TEXT, primary key (fecha,hora))";
    private String tbPuntuacionPreguntas="CREATE TABLE puntuacionPreguntas (fecha TEXT not null, hora TEXT not null,puntuacion NUMERIC, idUsuario INTEGER, idPregunta INTEGER, palabraPronunciada TEXT, primary key (fecha,hora))";

    public static ArrayList <String> palabrasImagenesRegistros=new ArrayList<>();

    //PARA ACTIVIDAD DE PREGUTNAS

    public static ArrayList <String> preguntasRegistros=new ArrayList<>();

    //private String actPreg1="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Choco entre dos paredes \nlate mi corazón \nQuien no sepa mi nombre \nes un cabezón.', 'el chocolate', 'chocolate', 'chocolate')";
    //private String actPreg2="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Blanca por dentro,\nverde por fuera. \nSi no sabes, \nespera.', 'la pera', 'pera', 'pera2')";
    //private String actPreg3="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Un señor gordito,\nmuy coloradito, \nno toma café, \nsiempre toma té', 'el tomate', 'tomate', 'tomate')";
    //private String actPreg4="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Oro parece, plata no es. \nAbran las cortinas, \ny verán lo que es.', 'el platano', 'platano', 'platano')";
    //private String actPreg5="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Lo come Pancracio, \nestá en el champán; \nsi piensas despacio \nsabrás que es el…', 'el pan', 'pan', 'pan')";
    //private String actPreg6="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Por dentro soy carbón,\npor fuera soy madera, \nviajo en tu estuche \ny me llevas a la escuela.', 'el lápiz', 'lápiz', 'lapiz')";
    //private String actPreg7="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Me llamo Leo,\nme apellido Pardo,\nquien no me adivine \nes un poco tardo. ', 'el leopardo', 'leopardo', 'leopardo')";
    //private String actPreg8="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Llevo mi casita a cuestas camino con una pata \ny voy dejando mi huella \ncon un hilito de plata.', 'el caracol', 'caracol', 'caracol')";
    //private String actPreg9="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Un animal que tiene \nojos de gato, \norejas de gato, \npatas de gato, \nrabo de gato \ny no es gato.', 'la gata', 'gata', 'gata')";
    //private String actPreg10="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Soy un animal muy elegante, \nmuy veloz y poco fiero; \ny cuando quiero calzarme \nvoy a casa del herrero. ', 'el caballo', 'caballo', 'caballo2')";
    //private String actPreg11="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Antes huevecito, \ndespués capullito,\nmás tarde volaré \ncomo un pajarito', 'la mariposa', 'mariposa', 'mariposa')";
    //private String actPreg12="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Soy astuto y juguetón\ny cazar un ratón\nes mi mayor afición.', 'el gato', 'gato', 'gato2')";
    //private String actPreg13="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Cuál es el animal,\nde campo o corral,\nque si una zanahoria le das\nsus dientecitos verás?', 'el conejo', 'conejo', 'conejo')";
    //private String actPreg14="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values(' Vuelo de noche, \nduermo de día \ny nunca verás plumas \nen el ala mía', 'el murciélago', 'murciélago', 'murcielago')";
    //private String actPreg15="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Tengo agujas pero no sé coser, \ntengo números pero no sé leer,\nlas horas te doy,\n¿sabes quién soy?', 'el reloj', 'reloj', 'reloj')";
    //private String actPreg16="INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Listo y grandullón, \nsi le preguntas no te habla.\nPero se sabe todas las respuestas,\nporque tiene todas las palabras.', 'el diccionario', 'diccionario', 'diccionario')";





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
        cargarArrayList();
    }

    private void cargarArrayList() {
        //PALABRAS
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('abuela', 'abuelita', '', '', 'abuela', 'la abuela nos quiere mucho')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('abuelo', 'abuelito', '', '', 'abuelo', 'el abuelo nos cuenta muchas historias')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('árbol', 'arbolito', '', '', 'arbol', 'el árbol de frutos')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('auto', 'autito', '', '', 'auto', 'un auto viaja muy rápido')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('avión', 'avioncito', '', '', 'avion', 'el avión vuela por todo el mundo')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('barco', 'barquito', '', '', 'barco', 'un barco sobre el mar')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('caballo', 'caballito', '', '', 'caballo', 'los caballos son muy rápidos')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('casa', 'casita', '', '', 'casa', 'una casa muy linda')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('cebra', '', '', '', 'cebra', 'la cebra tiene rayas negras')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('dinosaurio', 'dinosaurito', '', '', 'dinosaurio', 'los dinosaurios se extinguieron hace mucho')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('dragón', 'dragoncito', '', '', 'dragon', 'los dragones lanzan fuego')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('gato', 'gatito', '', '', 'gato', 'el gato maúlla')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('guerrero', 'soldado', '', '', 'guerrero', 'el guerrero defiende a las personas')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('guitarra', 'guitarra', '', '', 'guitarra', 'la guitarra produce música muy linda')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('jirafa', 'jirafa', '', '', 'jirafa', 'la jirafa tiene el cuello muy largo')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('ladrón', 'ratero', '', '', 'ladron', 'los ladrones son peligrosos')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('león', 'leoncito', '', '', 'leon', 'el león es el rey de la selva')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('libro', 'librito', 'letras', '', 'libro', 'los libros tienen muchas historias')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('luna', 'luna y estrellas', '', '', 'luna', 'la luna sale de noche rodeada de estrellas')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('manzana', '', '', '', 'manzana', 'la manzana es muy nutritiva')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('oso', 'osito', 'peluche', '', 'oso', 'el oso de peluche es muy suave')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('payaso', 'payasito', '', '', 'payaso', 'el payaso nos hace reír')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('pelota', 'balón', '', '', 'pelota', 'con la pelota podemos jugar muchas cosas')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('perro', 'perrito', '', '', 'perro', 'el perro es un buen amigo')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('pera', '', '', '', 'pera', 'la pera es una fruta ')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('piano', 'teclado', '', '', 'piano', 'el piano, o teclado, toca buenas melodías')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('pera', '', '', '', 'pera', 'la pera es una fruta ')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('planeta', 'tierra', 'mundo', '', 'planeta', 'todos vivimos en el planeta tierra')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('rana', 'sapo', 'ranita', 'sapito', 'rana', 'la rana salta muy alto')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('ratón', 'ratoncito', 'rata', '', 'raton', 'los ratones son pequeños')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('regalo', 'paquete', 'presente', '', 'regalo', 'un regalo misterioso es muy divertido')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('robot', 'robotito', '', '', 'robot', 'los robots pueden hacer muchas cosas')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('soldado', '', '', '', 'soldado', 'el soldado vive por la patria')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('tigre', '', '', '', 'tigre', 'el tigre vive en la selva')");
        palabrasImagenesRegistros.add("INSERT INTO actimagen (Palabra, palabra2, palabra3, palabra4, imagen, descripcion) values ('uvas', 'uvitas', '', '', 'uvas', 'las uvas crecen en los viñedos')");
        //PREGUNTAS
        preguntasRegistros.add("INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Choco entre dos paredes \nlate mi corazón \nQuien no sepa mi nombre \nes un cabezón.', 'el chocolate', 'chocolate', 'chocolate')");
        preguntasRegistros.add("INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Blanca por dentro,\nverde por fuera. Si no sabes, espera.', 'la pera', 'pera', 'pera2')");
        preguntasRegistros.add("INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Un señor gordito,\nmuy coloradito, \nno toma café, \nsiempre toma té', 'el tomate', 'tomate', 'tomate')");
        preguntasRegistros.add("INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Oro parece, plata no es. \nAbran las cortinas, \ny verán lo que es.', 'el platano', 'platano', 'platano')");
        preguntasRegistros.add("INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Lo come Pancracio, \nestá en el champán; \nsi piensas despacio \nsabrás que es el…', 'el pan', 'pan', 'pan')");
        preguntasRegistros.add("INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Por dentro soy carbón,\npor fuera soy madera, \nviajo en tu estuche \ny me llevas a la escuela.', 'el lápiz', 'lápiz', 'lapiz')");
        preguntasRegistros.add("INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Me llamo Leo,\nme apellido Pardo,\nquien no me adivine \nes un poco tardo. ', 'el leopardo', 'leopardo', 'leopardo')");
        preguntasRegistros.add("INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Llevo mi casita a cuestas camino con una pata \ny voy dejando mi huella \ncon un hilito de plata.', 'el caracol', 'caracol', 'caracol')");
        preguntasRegistros.add("INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Un animal que tiene \nojos de gato, \norejas de gato, \npatas de gato, \nrabo de gato \ny no es gato.', 'la gata', 'gata', 'gata')");
        preguntasRegistros.add("INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Soy un animal muy elegante, \nmuy veloz y poco fiero; \ny cuando quiero calzarme \nvoy a casa del herrero. ', 'el caballo', 'caballo', 'caballo2')");
        preguntasRegistros.add("INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Antes huevecito, \ndespués capullito,\nmás tarde volaré \ncomo un pajarito', 'la mariposa', 'mariposa', 'mariposa')");
        preguntasRegistros.add("INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Soy astuto y juguetón\ny cazar un ratón\nes mi mayor afición.', 'el gato', 'gato', 'gato2')");
        preguntasRegistros.add("INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Cuál es el animal,\nde campo o corral,\nque si una zanahoria le das\nsus dientecitos verás?', 'el conejo', 'conejo', 'conejo')");
        preguntasRegistros.add("INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values(' Vuelo de noche, \nduermo de día \ny nunca verás plumas \nen el ala mía', 'el murciélago', 'murciélago', 'murcielago')");
        preguntasRegistros.add("INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Tengo agujas pero no sé coser, \ntengo números pero no sé leer,\nlas horas te doy,\n¿sabes quién soy?', 'el reloj', 'reloj', 'reloj')");
        preguntasRegistros.add("INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Listo y grandullón, \nsi le preguntas no te habla.\nPero se sabe todas las respuestas,\nporque tiene todas las palabras.', 'el diccionario', 'diccionario', 'diccionario')");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creacion de tablas
        db.execSQL(tbUsuario);
        db.execSQL(tbPalabras);
        db.execSQL(tbPreguntas);
        db.execSQL(tbPuntuacionPalabras);
        db.execSQL(tbPuntuacionPreguntas);

        //insercion de palabras actImagenes

        for (int i=0; i<palabrasImagenesRegistros.size();i++){
            db.execSQL(palabrasImagenesRegistros.get(i));
        }

        for (int i=0; i<preguntasRegistros.size();i++){
            db.execSQL(preguntasRegistros.get(i));
        }

        /*db.execSQL( actImg1);
        db.execSQL( actImg2);
        db.execSQL( actImg3);...


        //insercion de preguntas
        db.execSQL(actPreg1);
        db.execSQL(actPreg2);... */

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
