package com.example.educationapp;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class MissingElement extends AppCompatActivity
{
    ImageView Question, ImgAnimation;
    TextInputEditText Answer;
    Button Submit, Check;
    int score = 0;
    int noQuestions = 0;

    int[] imageQuestions = { R.drawable.f1, R.drawable.f2, R.drawable.f3, R.drawable.f4, R.drawable.f5,
            R.drawable.f6, R.drawable.f7, R.drawable.f8, R.drawable.f9, R.drawable.f10,
            R.drawable.f11, R.drawable.f12, R.drawable.f13, R.drawable.f14, R.drawable.f15,
            R.drawable.f16, R.drawable.f17, R.drawable.f18, R.drawable.f19, R.drawable.f20,
            R.drawable.f21, R.drawable.f22, R.drawable.f23, R.drawable.f24, R.drawable.f25,
            R.drawable.f26, R.drawable.f27, R.drawable.f28, R.drawable.f29
    };

    String[] Answers = {"38 40","22 24","30 32","9","43 45","56 59","97 99","5","13","23","37",
                        "V","50","97","70","59","x","r","i","B",
                        "T","Q","E","I","4","k","6 8","3 5","9 11"};

    static int i=0;
    static boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing_element);
        Question = findViewById(R.id.imageViewMissing);
        Answer = findViewById(R.id.textInputEditTextAnswerMissing);
        Submit = findViewById(R.id.buttonSubmitMissing);
        Check = findViewById(R.id.buttonCheckMissing);
        ImgAnimation = findViewById(R.id.imageViewAnimatorMissing);
        nextQuestion();
        final TextView scoreMissing = findViewById(R.id.score_missing);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Answer.getText().toString().equals(Answers[i]))
                {
                    if(flag) {
                        score++;
                        CorrectAnimation();
                    }
                    nextQuestion();
                    scoreMissing.setText(score +"/"+(noQuestions-1));
                }
                else
                    WrongAnimation();
            }
        });


        Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Answer.setText(Answers[i]);
                Answer.setTextColor(0xFF00FF00);
                Submit.setText("NEXT");
                flag=false;
            }
        });

    }

    void nextQuestion()
    {
        noQuestions++;
        Random random = new Random();
        i = random.nextInt(29);
        Question.setImageResource(imageQuestions[i]);
        Answer.setText("");
        Answer.setTextColor(0xFFFFFFFF);
        flag=true;
        Submit.setText("SUBMIT");
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
}
