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

public class NumberRevision2 extends AppCompatActivity {
    TextView mTextTv;
    ImageButton mSpeakBtn, mVoiceBtn;
    TextView mQuestion;
    Button mNext;
    ImageView ImgQuestion;
    private TextToSpeech mTTS;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    static int a = 50;
    int[] numberImages= {
                R.drawable.n51, R.drawable.n52, R.drawable.n53, R.drawable.n54, R.drawable.n55,
                R.drawable.n56, R.drawable.n57, R.drawable.n58, R.drawable.n59, R.drawable.n60,
                R.drawable.n61, R.drawable.n62, R.drawable.n63, R.drawable.n64, R.drawable.n65,
                R.drawable.n66, R.drawable.n67, R.drawable.n68, R.drawable.n69, R.drawable.n70,
                R.drawable.n71, R.drawable.n72, R.drawable.n73, R.drawable.n74, R.drawable.n75,
                R.drawable.n76, R.drawable.n77, R.drawable.n78, R.drawable.n79, R.drawable.n80,
                R.drawable.n81, R.drawable.n82, R.drawable.n83, R.drawable.n84, R.drawable.n85,
                R.drawable.n86, R.drawable.n87, R.drawable.n88, R.drawable.n89, R.drawable.n90,
                R.drawable.n91, R.drawable.n92, R.drawable.n93, R.drawable.n94, R.drawable.n95,
                R.drawable.n96, R.drawable.n97, R.drawable.n98, R.drawable.n99, R.drawable.n100
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
                if(a==100 || mQuestion.getText().toString().equals("Hello"))
                    a=50;
                nextQuestion(++a);
            }
        });

    }

    private void nextQuestion(int i)
    {

        mQuestion.setText(Integer.toString(i));
        ImgQuestion.setImageResource(numberImages[i-51]);
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
                    if(result.get(0).equalsIgnoreCase(answer) || result.get(1).equalsIgnoreCase(answer)) {
                        voice("Correct");
                    }
                    else {
                        voice("Incorrect");
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
