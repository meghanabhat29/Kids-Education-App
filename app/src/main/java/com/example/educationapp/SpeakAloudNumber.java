package com.example.educationapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class SpeakAloudNumber extends AppCompatActivity {
    TextView mTextTv;
    ImageButton mVoiceBtn;
    ImageView ImgQuestion,ImgAnimation;
    TextView mQuestion;
    ImageButton mNext;
    private TextToSpeech mTTS;
    static int score = 0;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    ConstraintLayout status1;
    ConstraintLayout status2;

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
        setContentView(R.layout.activity_speak_aloud_number);
        mTextTv = findViewById(R.id.textTv);
        mVoiceBtn = findViewById(R.id.micBtn);
        ImgAnimation = findViewById(R.id.imageViewSAnimator);
        mQuestion = findViewById(R.id.textViewQuestion);
        mNext = findViewById(R.id.nextButton);
        status1 = findViewById(R.id.correct);
        status2 = findViewById(R.id.wrong);
        ImgQuestion = findViewById(R.id.imageViewNum);

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

        mTextTv.setText("RECORD");
        mNext.setVisibility(View.GONE);

        Random random = new Random();

        int i = random.nextInt(51);

        mQuestion.setText(Integer.toString(i));

        ImgQuestion.setImageResource(numberImages[i]);
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

    public void CorrectAnimation()
    {
        ImgAnimation.setVisibility(View.VISIBLE);
        ImgAnimation.setImageResource(R.drawable.correctanimation);
        final AnimationDrawable correctAnimation = (AnimationDrawable) ImgAnimation.getDrawable();
        correctAnimation.start();
    }

    public void WrongAnimation()
    {
        ImgAnimation.setVisibility(View.VISIBLE);
        ImgAnimation.setImageResource(R.drawable.wronganimation);
        final AnimationDrawable wrongAnimation = (AnimationDrawable) ImgAnimation.getDrawable();
        wrongAnimation.start();
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
                    if(result.get(0).equalsIgnoreCase(answer)) {
                        score++;
                        status2.setVisibility(View.GONE);
                        status1.setVisibility(View.VISIBLE);
                        mTextTv.setText("Correct Answer!");
                        mNext.setVisibility(View.VISIBLE);
                        CorrectAnimation();
                    }
                    else {
                        mTextTv.setText("Incorrect! Retry");
                        status1.setVisibility(View.GONE);
                        status2.setVisibility(View.VISIBLE);
                        mNext.setVisibility(View.VISIBLE);
                        WrongAnimation();
                    }
                    voice(mTextTv.getText().toString());
                }
                break;

            }


            default:
                throw new IllegalStateException("Unexpected value: " + requestCode);
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,NumberDashboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        super.onBackPressed();
    }
}
