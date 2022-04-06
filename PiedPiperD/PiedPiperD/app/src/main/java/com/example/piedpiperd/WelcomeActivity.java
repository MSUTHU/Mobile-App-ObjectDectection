package com.example.piedpiperd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.piedpiperd.tflite.Classifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity implements View.OnTouchListener {
    TextView mytextview,userVoice;
    TextToSpeech textToSpeech;
    Timer timer;
    FrameLayout frame;
    private static final  int REQ_CODE_SPEECH_INPUT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);



        //text to speech method
        frame = findViewById(R.id.click_frame);

        //system voice
 textToSpeech = new TextToSpeech(getApplicationContext(),
         new TextToSpeech.OnInitListener() {
             @Override
             public void onInit(int i) {
                 if (i == TextToSpeech.SUCCESS)
                 {
                     int language = textToSpeech.setLanguage(Locale.ENGLISH);
                 }
             }
         });
     frame.setOnTouchListener(this);



    }
    @Override
    protected void onStart() {
        super.onStart();

        SessionManagement session = new SessionManagement(getApplicationContext());
        session.firstTime();
    }
    /**
     * get the use speech
     */
/*
    private void getUserSpeech() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        try
        {
          startActivityForResult(intent,REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a)
        {

        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    userVoice.setText(result.get(0));
                    String voice = userVoice.getText().toString().toLowerCase();


                }    if (userVoice.getText().toString().equals("detection"))
                {
                    Intent intent = new Intent(WelcomeActivity.this, DetectorActivity.class);
                    startActivity(intent);


                }
                break;
            }

        }
    }
*/
/**
    //delay timer
 **/
    private void timerMethod()
    {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run()
            {
                Intent intent = new Intent(WelcomeActivity.this, DetectorActivity.class);
                startActivity(intent);
            }
        },10000);
    }

/**
    start the app speech by touch of a screen

**/
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)

    {
       switch (motionEvent.getAction())
       {
           case MotionEvent.ACTION_DOWN:
               String s ="welcome to Blind Assistant AI  developed by pied Piper lets start detecting ";
               int speech = textToSpeech.speak(s,TextToSpeech.QUEUE_FLUSH, null);;
               timerMethod();

               break;

           case MotionEvent.ACTION_MOVE:

               break;
           case MotionEvent.ACTION_UP:

               break;
       }

        return false;
    }
}