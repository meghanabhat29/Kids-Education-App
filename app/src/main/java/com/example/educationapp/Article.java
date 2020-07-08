package com.example.educationapp;

import  androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Article extends AppCompatActivity {

    Button Option1, Option2;
    TextView Question;
    ImageView ImgQuestion, ImgAnimation;
    int check=0;
    int score = 0;
    final int[] articleImages = {R.drawable.apple_article, R.drawable.bat_article, R.drawable.car_article,
            R.drawable.chair_article, R.drawable.egg_article, R.drawable.elephant_article,
            R.drawable.icecream, R.drawable.ostrich_article, R.drawable.owl_article,
            R.drawable.umbrella_article};
    final String[] articleQuestions = {"apple", "bat", "car", "chair", "egg", "elephant", "ice cream",
            "ostrich", "owl", "umbrella"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        //COLOR CHANGE NOT HAPPENING INSIDE FUNCTIONS, BUT HAPPENING HERE. CHECK LATER

//        Option1 = findViewById(R.id.button2);
//
//        Drawable buttonDrawable1 = Option1.getBackground();
//        buttonDrawable1 = DrawableCompat.wrap(buttonDrawable1);
//        DrawableCompat.setTint(buttonDrawable1, Color.RED);
//        Option1.setBackground(buttonDrawable1);





        setOnclick();

    }

    private void setOnclick() {
        Option1 = findViewById(R.id.button2);
        Option2 = findViewById(R.id.button4);
        Question = findViewById(R.id.textView2);
        ImgQuestion = findViewById(R.id.imageViewArticle);
        ImgAnimation = findViewById(R.id.imageViewAnimator);
        final TextView scoreArticle = findViewById(R.id.score_article);


        final char startingAlphabet = Question.getText().toString().charAt(0);

        if (isVowel(startingAlphabet)) {
            Option1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {

                    Drawable buttonDrawable1 = Option1.getBackground();
                    buttonDrawable1 = DrawableCompat.wrap(buttonDrawable1);
                    DrawableCompat.setTint(buttonDrawable1 , Color.RED);
                    Option1.setBackground(buttonDrawable1 );
                    DrawableCompat.setTint(buttonDrawable1 , Color.GREEN);
                    Option2.setBackground(buttonDrawable1 );
                    new CountDownTimer(1000, 1) {
                        public void onFinish() {
                            nextQuestion();
                        }

                        public void onTick(long millisUntilFinished) {
                            // millisUntilFinished    The amount of time until finished.
                        }
                    }.start();
                    WrongAnimation();
                }
            });

            Option2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Drawable buttonDrawable1 = Option1.getBackground();
                    buttonDrawable1 = DrawableCompat.wrap(buttonDrawable1);
                    DrawableCompat.setTint(buttonDrawable1 , Color.RED);
                    Option1.setBackground(buttonDrawable1 );
                    DrawableCompat.setTint(buttonDrawable1 , Color.GREEN);
                    Option2.setBackground(buttonDrawable1 );

                    new CountDownTimer(1000, 1) {
                        public void onFinish() {
                            nextQuestion();
                        }

                        public void onTick(long millisUntilFinished) {
                            // millisUntilFinished    The amount of time until finished.
                        }
                    }.start();
                    score++;
                    CorrectAnimation();


                }
            });
        }
        else {
            Option1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Drawable buttonDrawable1 = Option1.getBackground();
                    buttonDrawable1 = DrawableCompat.wrap(buttonDrawable1);
                    DrawableCompat.setTint(buttonDrawable1 , Color.GREEN);
                    Option1.setBackground(buttonDrawable1 );
                    DrawableCompat.setTint(buttonDrawable1 , Color.RED);
                    Option2.setBackground(buttonDrawable1 );

                    new CountDownTimer(1000, 1) {
                        public void onFinish() {
                            nextQuestion();
                        }

                        public void onTick(long millisUntilFinished) {
                            // millisUntilFinished    The amount of time until finished.
                        }
                    }.start();
                    score++;
                    CorrectAnimation();

                }
            });

            Option2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Drawable buttonDrawable1 = Option1.getBackground();
                    buttonDrawable1 = DrawableCompat.wrap(buttonDrawable1);
                    DrawableCompat.setTint(buttonDrawable1 , Color.GREEN);
                    Option1.setBackground(buttonDrawable1 );
                    DrawableCompat.setTint(buttonDrawable1 , Color.RED);
                    Option2.setBackground(buttonDrawable1 );
                    new CountDownTimer(1000, 1) {
                        public void onFinish() {
                            nextQuestion();
                        }

                        public void onTick(long millisUntilFinished) {
                            // millisUntilFinished    The amount of time until finished.
                        }
                    }.start();
                    WrongAnimation();
                }
            });
        }
        scoreArticle.setText(Integer.toString(score));
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
    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    private void nextQuestion()
    {

        // GET NEXT TEXT


        Option1 = findViewById(R.id.button2);
        Option2 = findViewById(R.id.button4);
        Question = findViewById(R.id.textView2);

        check++;
        ImgQuestion.setImageResource(articleImages[check%articleImages.length]);
        Question.setText(articleQuestions[check%articleQuestions.length]);

        //Question.setText("next_text");
        Drawable buttonDrawable1 = Option1.getBackground();
        buttonDrawable1 = DrawableCompat.wrap(buttonDrawable1);
        DrawableCompat.setTint(buttonDrawable1, Color.WHITE);
        Option1.setBackground(buttonDrawable1);
        Option2.setBackground(buttonDrawable1);

        setOnclick();
    }



}
