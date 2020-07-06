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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class NumberRevision extends AppCompatActivity {
    TextView mTextTv;
    ImageButton mSpeakBtn, mVoiceBtn;
    TextView mQuestion;
    Button mNext;
    ImageView ImgQuestion;
    private TextToSpeech mTTS;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    static int a = -1;
    int[] numberImages= { R.drawable.n0,
            R.drawable.n1, R.drawable.n2, R.drawable.n3, R.drawable.n4, R.drawable.n5,
            R.drawable.n6, R.drawable.n7, R.drawable.n8, R.drawable.n9, R.drawable.n10,
            R.drawable.n11, R.drawable.n12, R.drawable.n13, R.drawable.n14, R.drawable.n15,
            R.drawable.n16, R.drawable.n17, R.drawable.n18, R.drawable.n19, R.drawable.n20,
            R.drawable.n21, R.drawable.n22, R.drawable.n23, R.drawable.n24, R.drawable.n25,
            R.drawable.n26, R.drawable.n27, R.drawable.n28, R.drawable.n29, R.drawable.n30,
            R.drawable.n31, R.drawable.n32, R.drawable.n33, R.drawable.n34, R.drawable.n35,
            R.drawable.n36, R.drawable.n37, R.drawable.n38, R.drawable.n39, R.drawable.n40,
            R.drawable.n41, R.drawable.n42, R.drawable.n43, R.drawable.n44, R.drawable.n45,
            R.drawable.n46, R.drawable.n47, R.drawable.n48, R.drawable.n49, R.drawable.n50
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_revision);
        mTextTv = findViewById(R.id.textTv);
        mQuestion = findViewById(R.id.textViewQuestion);
        mVoiceBtn = findViewById(R.id.micBtn);
        mNext = findViewById(R.id.buttonNext);
        mSpeakBtn = findViewById(R.id.imageViewSpeak);
        ImgQuestion = findViewById(R.id.imageViewNumber);


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
                mVoiceBtn.setVisibility(View.VISIBLE);
                mSpeakBtn.setVisibility(View.VISIBLE);
                mNext.setText("NEXT");
                if(a==50 || mQuestion.getText().toString().equals("Hello"))
                    a=-1;
                nextQuestion(++a);
            }
        });

    }

    private void nextQuestion(int i)
    {
        mQuestion.setText(Integer.toString(i));
        ImgQuestion.setImageResource(numberImages[i]);
    }

    private void voice(String text)
    {
        mTTS.setPitch((float) 1.0);
        mTTS.setSpeechRate((float) 1.0);
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
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
                    if(result.get(0).equalsIgnoreCase(answer)) {
                        voice("Correct");
                    }
                    else {
                        voice(result.get(0));
                    }
                }
                break;

            }


            default:
                throw new IllegalStateException("Unexpected value: " + requestCode);
        }
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

}
