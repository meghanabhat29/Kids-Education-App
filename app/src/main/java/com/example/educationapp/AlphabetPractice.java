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

public class AlphabetPractice extends AppCompatActivity {
    TextView mTextTv;
    ImageButton mSpeakBtn, mVoiceBtn;
    TextView mQuestion;
    Button mNext;
    ImageView capitalImage, smallImage;
    private TextToSpeech mTTS;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    static int a = 65;

    int[] capitalLetters= { R.drawable.a,
            R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f,
            R.drawable.g, R.drawable.h, R.drawable.i, R.drawable.j, R.drawable.k,
            R.drawable.l, R.drawable.m, R.drawable.n, R.drawable.o, R.drawable.p,
            R.drawable.q, R.drawable.r, R.drawable.s, R.drawable.t, R.drawable.u,
            R.drawable.v, R.drawable.w, R.drawable.x, R.drawable.y, R.drawable.z,
    };

    int[] smallLetters= { R.drawable.sa,
            R.drawable.sb, R.drawable.sc, R.drawable.sd, R.drawable.se, R.drawable.sf,
            R.drawable.sg, R.drawable.sh, R.drawable.si, R.drawable.sj, R.drawable.sk,
            R.drawable.sl, R.drawable.sm, R.drawable.sn, R.drawable.so, R.drawable.sp,
            R.drawable.sq, R.drawable.sr, R.drawable.ss, R.drawable.st, R.drawable.su,
            R.drawable.sv, R.drawable.sw, R.drawable.sx, R.drawable.sy, R.drawable.sz,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet_practice);
        mTextTv = findViewById(R.id.textTv);
        mQuestion = findViewById(R.id.textViewQuestion);
        mVoiceBtn = findViewById(R.id.micBtn);
        mNext = findViewById(R.id.buttonNext);
        mSpeakBtn = findViewById(R.id.imageViewSpeak);
        capitalImage = findViewById(R.id.imageViewAlphabet);
        smallImage = findViewById(R.id.imageViewSmallPractice);

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
                if(mQuestion.getText().toString().equals("Hello"))
                    voice("Press Next");
                else {
                    String question = mQuestion.getText().toString().substring(0, 1);
                    voice(question);
                }
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
                if(a==90 || mQuestion.getText().toString().equals("Hello"))
                    a=64;
                nextQuestion(++a);
            }
        });

    }

    private void nextQuestion(int i)
    {

        char capital = (char) i;
        char small = (char) (i+32);

        mQuestion.setText(Character.toString(capital)+" "+Character.toString(small));
        capitalImage.setImageResource(capitalLetters[i-65]);
        smallImage.setImageResource(smallLetters[i-65]);
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
                    String answer = mQuestion.getText().toString().substring(2);
                    if(result.get(0).equalsIgnoreCase(answer) || result.get(0).contains(answer))
                    {
                        voice("Correct");
                    }
                    else {
                        voice("Incorrect");
                        Toast.makeText(this, ""+result.get(0), Toast.LENGTH_SHORT).show();

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
