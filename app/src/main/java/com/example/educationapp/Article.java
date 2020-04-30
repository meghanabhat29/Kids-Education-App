package com.example.educationapp;

import  androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Article extends AppCompatActivity {

    Button Option1, Option2;
    TextView Question;

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
                    Toast.makeText(Article.this, "WRONG ANSWER", Toast.LENGTH_SHORT).show();
                    new CountDownTimer(1000, 1) {
                        public void onFinish() {
                            nextQuestion();
                        }

                        public void onTick(long millisUntilFinished) {
                            // millisUntilFinished    The amount of time until finished.
                        }
                    }.start();
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
                    Toast.makeText(Article.this, "CORRECT ANSWER", Toast.LENGTH_SHORT).show();
                    new CountDownTimer(1000, 1) {
                        public void onFinish() {
                            nextQuestion();
                        }

                        public void onTick(long millisUntilFinished) {
                            // millisUntilFinished    The amount of time until finished.
                        }
                    }.start();
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
                    Toast.makeText(Article.this, "CORRECT ANSWER", Toast.LENGTH_SHORT).show();
                    new CountDownTimer(1000, 1) {
                        public void onFinish() {
                            nextQuestion();
                        }

                        public void onTick(long millisUntilFinished) {
                            // millisUntilFinished    The amount of time until finished.
                        }
                    }.start();
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
                    Toast.makeText(Article.this, "WRONG ANSWER", Toast.LENGTH_SHORT).show();
                    new CountDownTimer(1000, 1) {
                        public void onFinish() {
                            nextQuestion();
                        }

                        public void onTick(long millisUntilFinished) {
                            // millisUntilFinished    The amount of time until finished.
                        }
                    }.start();
                }
            });
        }
    }

    private boolean isVowel(char c) {
        if (c=='a'|| c=='e'|| c=='i'|| c=='o'||c=='u')
            return true;
        return false;
    }

    private void nextQuestion()
    {

        // GET NEXT TEXT


        Option1 = findViewById(R.id.button2);
        Option2 = findViewById(R.id.button4);
        Question = findViewById(R.id.textView2);

        Question.setText("next_text");
        Drawable buttonDrawable1 = Option1.getBackground();
        buttonDrawable1 = DrawableCompat.wrap(buttonDrawable1);
        DrawableCompat.setTint(buttonDrawable1, Color.WHITE);
        Option1.setBackground(buttonDrawable1);
        Option2.setBackground(buttonDrawable1);
        setOnclick();

    }
}
