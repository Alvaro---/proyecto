package com.ex.alvaro.pronunciatel;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.util.Log;

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