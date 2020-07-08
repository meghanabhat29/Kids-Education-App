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

public class SpeakAloudNumber2 extends AppCompatActivity {
    TextView mTextTv;
    ImageButton mVoiceBtn;
    TextView mQuestion;
    ImageButton mNext;
    private TextToSpeech mTTS;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    static int score = 0;
    ConstraintLayout status1;
    ImageView ImgQuestion, ImgAnimation;
    ConstraintLayout status2;
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
        setContentView(R.layout.activity_speak_aloud_number);
        mTextTv = findViewById(R.id.textTv);
        mVoiceBtn = findViewById(R.id.micBtn);
        mQuestion = findViewById(R.id.textViewQuestion);
        ImgAnimation = findViewById(R.id.imageViewSAnimator);
        mNext = findViewById(R.id.nextButton);
        status1 = findViewById(R.id.correct);
        status2 = findViewById(R.id.wrong);
        ImgQuestion = findViewById(R.id.imageViewNum);
        nextQuestion();


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

        int i = random.nextInt(51) +50;

        mQuestion.setText(Integer.toString(i));
        ImgQuestion.setImageResource(numberImages[i-51]);
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
        Intent intent = new Intent(this,NumberDashboard2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        super.onBackPressed();
    }
}
