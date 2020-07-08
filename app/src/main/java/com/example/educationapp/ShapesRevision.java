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

public class ShapesRevision extends AppCompatActivity {
    TextView mTextTv;
    ImageButton mSpeakBtn, mVoiceBtn;
    TextView mQuestion;
    Button mNext;
    ImageView ImgQuestion;
    private TextToSpeech mTTS;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    static int a = -1;
    int[] shapeImages= {
            R.drawable.shape8,R.drawable.shape1, R.drawable.shape16, R.drawable.shape6,
            R.drawable.shape11, R.drawable.shape18, R.drawable.shape24, R.drawable.shape10,
            R.drawable.shape25, R.drawable.shape13,R.drawable.shape2,R.drawable.shape5,
            R.drawable.shape4,R.drawable.shape7,R.drawable.shape12,R.drawable.shape9,
            R.drawable.shape14, R.drawable.shape15,R.drawable.shape23,
            R.drawable.shape10, R.drawable.shape11};


    String[] shapeNames = { "Circle","Triangle", "Square", "Rectangle",
            "Pentagon","Hexagon","Heptagon","Octagon",
            "Nonagon","Decagon","Crescent","Rhombus",
            "Paralellogram","Trapezium","Ellipse","Heart",
            "Star","Arrow","Kite"};


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shapes_revision);
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
                if(a==18 || mQuestion.getText().toString().equals("Hello"))
                    a=-1;
                nextQuestion(++a);
            }
        });

    }

    private void nextQuestion(int i)
    {
        mQuestion.setText(shapeNames[i]);
        ImgQuestion.setImageResource(shapeImages[i]);
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
                    if(result.get(0).equalsIgnoreCase(answer))
                    {
                        voice("Correct");
                    }
                    else {
                        voice("Incorrect");
                        //Toast.makeText(this, ""+result.get(0), Toast.LENGTH_SHORT).show();

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
