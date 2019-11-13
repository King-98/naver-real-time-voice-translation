package com.ncp.ai.demo.process;

import com.naver.speech.clientapi.SpeechRecognitionListener;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.clova_sample_2.R;

import com.naver.speech.clientapi.SpeechConfig;
import com.naver.speech.clientapi.SpeechRecognitionException;
import com.naver.speech.clientapi.SpeechRecognitionListener;
import com.naver.speech.clientapi.SpeechRecognitionResult;
import com.naver.speech.clientapi.SpeechRecognizer;


public class CsrProc implements SpeechRecognitionListener {

    private final static String TAG = CsrProc.class.getSimpleName();

    private Handler mHandler;
    private SpeechRecognizer mRecognizer;

    private static CsrProc instance;

    public void setHandler(Handler handler) {

        this.mHandler = handler;

    }

    public CsrProc(Context context, String clientId) {

        try {
            mRecognizer = new SpeechRecognizer(context, clientId);

        } catch (SpeechRecognitionException e) {
            e.printStackTrace();
        }

        mRecognizer.setSpeechRecognitionListener(this);
    }

    public static CsrProc getCsrProc(Context context,  String clientId) {

        if(instance == null) {

            instance = new CsrProc(context, clientId);
        }

        return instance;
    }

    public SpeechRecognizer getSpeechRecognizer() {
        return mRecognizer;
    }

    public void recognize() {
        try {

            if (mRecognizer.recognize(new SpeechConfig(
                    SpeechConfig.LanguageType.KOREAN,
                    SpeechConfig.EndPointDetectType.AUTO))){

                System.out.println("### true");

            }else {
                System.out.println("### false");
            }
        } catch (SpeechRecognitionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onInactive() {
        Log.d(TAG, "Event occurred : Inactive");
        Message msg = Message.obtain(mHandler, R.id.clientInactive);
        msg.sendToTarget();
    }

    @Override
    public void onReady() {
        Log.d(TAG, "Event occurred : Ready");
        Message msg = Message.obtain(mHandler, R.id.clientReady);
        msg.sendToTarget();
    }

    @Override
    public void onRecord(short[] speech) {
        Log.d(TAG, "Event occurred : Record");
        Message msg = Message.obtain(mHandler, R.id.audioRecording, speech);
        msg.sendToTarget();
    }

    @Override
    public void onPartialResult(String result) {
        Log.d(TAG, "Partial Result!! (" + result + ")");
        Message msg = Message.obtain(mHandler, R.id.partialResult, result);
        msg.sendToTarget();
    }

    @Override
    public void onEndPointDetected() {
        Log.d(TAG, "Event occurred : EndPointDetected");
    }

    @Override
    public void onResult(SpeechRecognitionResult result) {
        Log.d(TAG, "Final Result!! (" + result.getResults().get(0) + ")");
        Message msg = Message.obtain(mHandler, R.id.finalResult, result);
        msg.sendToTarget();
    }

    @Override
    public void onError(int errorCode) {
        Log.d(TAG, "Error!! (" + Integer.toString(errorCode) + ")");
        Message msg = Message.obtain(mHandler, R.id.recognitionError, errorCode);
        msg.sendToTarget();
    }

    @Override
    public void onEndPointDetectTypeSelected(SpeechConfig.EndPointDetectType epdType) {
        Log.d(TAG, "EndPointDetectType is selected!! (" + Integer.toString(epdType.toInteger()) + ")");
        Message msg = Message.obtain(mHandler, R.id.endPointDetectTypeSelected, epdType);
        msg.sendToTarget();
    }

}
