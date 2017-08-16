package com.nativemoduletest;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.widget.Toast;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.List;

import javax.annotation.Nullable;

public class SpeechToTextModule extends ReactContextBaseJavaModule implements ActivityEventListener {

    private final int SPEECH_RECOGNITION_CODE = 1;
    private Promise speech;
    private ReactApplicationContext mReactContext;

    public SpeechToTextModule(ReactApplicationContext reactContext) {
        super(reactContext);
        reactContext.addActivityEventListener(this);
        mReactContext = reactContext;
    }

    @ReactMethod
    public void writeSpeech(Promise promise) {
        
    }

    public void speechToText() {
        // Showing google speech input dialog

    }

    @Override
    public String getName() {
        return "SpeechToText";
    }

    
    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        // Receiving speech input
    }

    @Override
    public void onNewIntent(Intent intent) {

    }
}
