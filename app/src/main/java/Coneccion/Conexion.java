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

    private String tbCuentos="CREATE TABLE cuentos (id INTEGER PRIMARY KEY, cuento TEXT)";
    private String tbPreguntasCuento="CREATE TABLE preguntascuento(id INTEGER PRIMARY KEY AUTOINCREMENT, pregunta TEXT, respuesta1 TEXT, respuesta2 TEXT, respuesta3 TEXT, idcuento INTEGER, FOREIGN KEY(idcuento) REFERENCES tbcuentos(id))";

    private String tbCadenaFonematica="CREATE TABLE cadena (id INTEGER PRIMARY KEY AUTOINCREMENT, imagen TEXT, palabra TEXT, letra TEXT, inicial INTEGER, final INTEGER, inversa INTEGER, media INTEGER)";

    public static ArrayList <String> palabrasImagenesRegistros=new ArrayList<>();

    //PARA ACTIVIDAD DE PREGUTNAS

    public static ArrayList <String> preguntasRegistros=new ArrayList<>();

    //Actividad Para lectura de Cuentos

    public static ArrayList <String> cuentos=new ArrayList<>();

    public static ArrayList <String> preguntaCuentoRegistros=new ArrayList<>();

    public static ArrayList <String> letrasCadenaFonematicaRegistros=new ArrayList<>();



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
        preguntasRegistros.add("INSERT INTO actpreguntas (pregunta,palabra,palabra2,imagen) values('Choco entre dos paredes \nlate mi corazón. \nQuien no sepa mi nombre \nes un cabezón.', 'el chocolate', 'chocolate', 'chocolate')");
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

        //CUENTOS
        cuentos.add("INSERT INTO cuentos (id, cuento) values ( 1, " +
                "'Un pajarito estaba encerrado en su jaula de oro. \n Llegó el invierno y los niños jugaron con la nieve.\nLlegó la primavera y los niños jugaron con las flores.\nLlegó el verano y el pajarito se escapó para jugar con el mar')");


        //PREGUNTAS CUENTOS
        preguntaCuentoRegistros.add("INSERT INTO preguntascuento (pregunta, respuesta1, respuesta2, respuesta3, idcuento) VALUES ('¿Cómo estaba el pajarito en su jaula?','Encerrado','Alegre','Corriendo','1')");
        preguntaCuentoRegistros.add("INSERT INTO preguntascuento (pregunta, respuesta1, respuesta2, respuesta3, idcuento) VALUES ('¿Cuándo jugaban los niños a tirarse hojas?','Otoño','Primavera','Invierno','1')");
        preguntaCuentoRegistros.add("INSERT INTO preguntascuento (pregunta, respuesta1, respuesta2, respuesta3, idcuento) VALUES ('El pajarito se escapó para jugar con el mar en:','Verano','Otoño','Invierno','1')");

        /*
        private String tbPreguntasCuento="CREATE TABLE preguntascuento(id INTEGER PRIMARY KEY AUTOINCREMENT, pregunta TEXT, respuesta1 TEXT, respuesta2 TEXT, respuesta3 TEXT, idcuento INTEGER FOREIGN KEY REFERENCES tbcuentos(id)";

      ¿Cómo estaba el pajarito en su jaula?', 'Alegre', 'Con ganas de escapar', 'Muy feliz'," +
                "'¿Cuándo jugaban los niños a tirarse hojas?','En invierno','En otoño','En primavera'," +
                        "'El pajarito se escapó para jugar con el mar:','En primavera','En verano','En otoño'))*/


        //CADENA FONEMATICA
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra) values ('ala','ala','A')");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra) values ('aba','haba','A')");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra) values ('papa','papa','A')");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra) values ('tapa','tapa','A')");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra) values ('pala','pala','A')");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra) values ('mama','mamá','A')");
        //letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra) values ('taza','taza','A')");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra) values ('lata','lata','A')");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra) values ('rama','rama','A')");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra) values ('mapa','mapa','A')");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra) values ('gata','gata','A')");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra) values ('cama','cama','A')");

        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('ballena','ballena','B-V', 1, 0)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('balon','balon','B-V', 1, 0)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('bata','bata','B-V', 1, 0)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('barco','barco','B-V', 1, 0)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('bicho','bicho','B-V', 1, 0)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('bigote','bigote','B-V', 1, 0)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('bote','bote','B-V', 1, 0)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('botella','botella','B-V', 1, 0)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('buho','buho','B-V', 1, 0)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('vaca','vaca','B-V', 1, 0)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('vaso','vaso','B-V', 1, 0)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('vela','vela','B-V', 1, 0)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('vino','vino','B-V', 1, 0)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('violin','violin','B-V', 1, 0)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('bebida','bebida','B-V', 0, 1)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('llave','llave','B-V', 0, 1)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('lobo','lobo','B-V', 0, 1)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('uvas','uvas','B-V', 0, 1)");

        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('silla','silla','C-S-Z', 1, 0)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('sandia','sandia','C-S-Z', 1, 0)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('sol','sol','C-S-Z', 1, 0)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('zapato','zapato','C-S-Z', 1, 0)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('cesto','cesto','C-S-Z', 1, 0)");

        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('oso','oso','C-S-Z', 0, 1)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('queso','queso','C-S-Z', 0, 1)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('pozo','pozo','C-S-Z', 0, 1)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('rosa','rosa','C-S-Z', 0, 1)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('beso','beso','C-S-Z', 0, 1)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('mesa','mesa','C-S-Z', 0, 1)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media) values ('vaso','vaso','C-S-Z', 0, 1)");

        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media, final, inversa) values ('guantes','guantes','C-S-Z', 0, 0,1,0)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media, final, inversa) values ('pez','pez','C-S-Z', 0, 0,1,0)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media, final, inversa) values ('medias','medias','C-S-Z', 0, 0,1,0)");

        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media, final, inversa) values ('asno','asno','C-S-Z', 0, 0,0,1)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media, final, inversa) values ('vestido','vestido','C-S-Z', 0, 0,0,1)");
        letrasCadenaFonematicaRegistros.add("insert into cadena (imagen, palabra, letra, inicial, media, final, inversa) values ('pez','pez','C-S-Z', 0, 0,0,1)");


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //creacion de tablas
        db.execSQL(tbUsuario);
        db.execSQL(tbPalabras);
        db.execSQL(tbPreguntas);
        db.execSQL(tbPuntuacionPalabras);
        db.execSQL(tbPuntuacionPreguntas);
        db.execSQL(tbCuentos);
        db.execSQL(tbPreguntasCuento);
        db.execSQL(tbCadenaFonematica);

        //insercion de palabras actImagenes

        for (int i=0; i<palabrasImagenesRegistros.size();i++){
            db.execSQL(palabrasImagenesRegistros.get(i));
        }

        for (int i=0; i<preguntasRegistros.size();i++){
            db.execSQL(preguntasRegistros.get(i));
        }

        for (int i=0; i<cuentos.size();i++){
            db.execSQL(cuentos.get(i));
        }

        for (int i=0; i<preguntaCuentoRegistros.size();i++){
            db.execSQL(preguntaCuentoRegistros.get(i));
        }

        /*db.execSQL( actImg1);
        db.execSQL( actImg2);
        db.execSQL( actImg3);...


        //insercion de preguntas
        db.execSQL(actPreg1);
        db.execSQL(actPreg2);... */

        for (int i=0; i<letrasCadenaFonematicaRegistros.size();i++){
            db.execSQL(letrasCadenaFonematicaRegistros.get(i));
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
