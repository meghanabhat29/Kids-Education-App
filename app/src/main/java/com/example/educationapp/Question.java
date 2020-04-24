package com.example.educationapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class Question extends AppCompatActivity {
    TextView mTextTv;
    ImageButton mVoiceBtn, mSpeakBtn;
    TextView mQuestion;
    Button mNext;
    private TextToSpeech mTTS;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        mTextTv = findViewById(R.id.textTv);
        mVoiceBtn = findViewById(R.id.micBtn);
        mQuestion = findViewById(R.id.textViewQuestion);
        mNext = findViewById(R.id.buttonNext);
        mSpeakBtn = findViewById(R.id.imageViewSpeak);

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS)
                {
                   int result =  mTTS.setLanguage(Locale.ENGLISH);
                   if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                       Log.e("TTS","Language not supported");
                }
                else
                    Log.e("TTS","Initialisation Failed!");
            }
        });

        mSpeakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String question = mQuestion.getText().toString();
                voice(question);
            }
        });


        mVoiceBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                speak();
            }
        });

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                nextQuestion();
            }
        });

    }

    private void nextQuestion()
    {

            String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String fullalphabet = alphabet + alphabet.toLowerCase() + "0123456789";
            Random random = new Random();

            char code = fullalphabet.charAt(random.nextInt(61));

            mQuestion.setText(Character.toString(code));
    }

    private void speak()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hi Speak Something");

        try
        {
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        }

        catch (Exception e)
        {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void voice(String text)
    {
        mTTS.setPitch((float) 1.0);
        mTTS.setSpeechRate((float) 1.0);
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy()
    {
        if(mTTS != null)
        {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case REQUEST_CODE_SPEECH_INPUT:{
                if(resultCode == RESULT_OK && null!=data)
                {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String answer = mQuestion.getText().toString();
                    if(result.get(0).equalsIgnoreCase(answer) || result.get(1).equalsIgnoreCase(answer)) {
                        mTextTv.setText("Correct!");
                        mTextTv.setTextColor(0xFF00FF00);
                    }
                    else {
                        mTextTv.setText("Incorrect!");
                        mTextTv.setTextColor(0xFFF8070F);
                    }
                    voice(mTextTv.getText().toString());
                }
                break;

            }


            default:
                throw new IllegalStateException("Unexpected value: " + requestCode);
        }
    }
}
