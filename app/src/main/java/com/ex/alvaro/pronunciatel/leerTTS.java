package com.ex.alvaro.pronunciatel;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by Alvaro on 19/03/2015.
 */
public class leerTTS extends Service implements TextToSpeech.OnInitListener {

    private String str;
    private TextToSpeech mTts;
    private static final String TAG="TTSService";

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        mTts = new TextToSpeech(this, this/* OnInitListener*/);
        mTts.setSpeechRate(1f);
        Log.v(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        String leeme = intent.getExtras().getString("leeme");
        str=leeme;
        speak(str);
        Log.v(TAG, "onStart -");
        super.onStart(intent, startId);
    }

    @Override
    public void onInit(int status) {
        Log.v(TAG, "oninit");
        if (status == TextToSpeech.SUCCESS) {
            Locale loc = new Locale ("spa", "ESP");
            mTts.setLanguage(loc);
            int result = mTts.setLanguage(loc);
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                    result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.v(TAG, "Lenguaje no disponible");
            }else{
                speak(str);
            }
        } else {
            Log.v(TAG, "Could not initialize TextToSpeech.");
        }
    }

    private void speak(String str) {
        Log.v(TAG, "SPEAK");
        mTts.speak(str,TextToSpeech.QUEUE_FLUSH,null);
    }

    @Override
    public void onDestroy() {
        if (mTts != null) {
            mTts.stop();
            mTts.shutdown();
        }
        super.onDestroy();
    }
}
  /*





    private TextToSpeech myTTS;
    private int MY_DATA_CHECK_CODE=0;

    public leerTTS (){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Inicia intent de habla
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
    }

    public void speak(String leer) {
        myTTS.speak(leer, TextToSpeech.QUEUE_FLUSH, null);
        //va tercero
        //Toast.makeText(this,"SPEAK",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInit(int status) {
        //va segundo
        //Toast.makeText(this,"INIT",Toast.LENGTH_LONG).show();
        if (status==TextToSpeech.SUCCESS){
            Locale loc = new Locale ("spa", "ESP");
            myTTS.setLanguage(loc);
        }else if (status==TextToSpeech.ERROR){
            Toast.makeText(this, "Error.. ", Toast.LENGTH_LONG).show();
        }
    }

    protected void onActivityResult (int request_code, int result_code, Intent data){
        //va primero 2 veces
        //Toast.makeText(this,"ACTIVITYRESULT",Toast.LENGTH_LONG).show();
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
*/