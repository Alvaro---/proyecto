package com.ex.alvaro.pronunciatel;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Alvaro on 19/03/2015.
 */
public class reconocerVoz extends Service implements RecognitionListener {

    private String TAG="reconocer Voz";
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private String LOG_TAG = "Reconocer Voz";

    public String a;

    static String texto="";
    public static ArrayList<String> resultados=null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.v(TAG, "onCreate");
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(this);

        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,"en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,this.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
        speech.startListening(recognizerIntent);

        Toast.makeText(getApplicationContext(),"onCreate",Toast.LENGTH_LONG).show();
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
//        a=intent.getExtras().getString("clase");
        vaciarResultados();
        Log.v(TAG, "onStart");
        super.onStart(intent, startId);
    }

    @Override
    public void onBeginningOfSpeech() {
        Log.i(LOG_TAG, "onBeginningOfSpeech");
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        Log.i(LOG_TAG, "onBufferReceived: " + buffer);
    }

    @Override
    public void onEndOfSpeech() {
        Log.i(LOG_TAG, "onEndOfSpeech");
        this.onDestroy();
    }

    @Override
    public void onError(int errorCode) {
        String errorMessage = getErrorText(errorCode);
        Log.d(LOG_TAG, "FAILED " + errorMessage);
        //texto = errorMessage;
    }

    @Override
    public void onEvent(int arg0, Bundle arg1) {
        Log.i(LOG_TAG, "onEvent");
    }

    @Override
    public void onPartialResults(Bundle arg0) {
        Log.i(LOG_TAG, "onPartialResults");
    }

    @Override
    public void onReadyForSpeech(Bundle arg0) {
        Log.i(LOG_TAG, "onReadyForSpeech");
    }

    @Override
    public void onResults(Bundle results) {
        Log.i(LOG_TAG, "onResults");
        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        // Se convertira en cadena de texto para ver si es que se capto el audio mas facil.
        String text = "";
        for (String result : matches)
            text += result + "\n";
        //Se copia el texto y el array list a las variables globales staticas
        setTexto(text);
        setResultados(matches);

        //Mostrar en pantalla el resultado
        /*
        Toast.makeText(getApplicationContext(),"Resultados: "+texto,Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG, texto);*/

    }

    @Override
    public void onRmsChanged(float rmsdB) {
        Log.i(LOG_TAG, "onRmsChanged: " + rmsdB);
    }

    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Error en grabacion de audio";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Error en tu movil";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Permisos insuficientes";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Error de red";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "No se puede conectar a la red";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No hay coincidencias";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "El servicio esta ocupado...";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error del servidor";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No se detecto entrada de voz";
                break;
            default:
                message = "No entiendo... de nuevo por favor.";
                break;
        }
        return message;
    }

    public static String getTexto() {
        return texto;
    }

    public static void setTexto(String texto) {
        reconocerVoz.texto = texto;
    }

    public static ArrayList<String> getResultados() {
        return resultados;
    }

    public static void setResultados(ArrayList<String> resultados) {
        reconocerVoz.resultados = resultados;
    }
    private void vaciarResultados(){
        texto="";
        resultados=null;
    }
}
