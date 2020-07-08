package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Random;

public class NumberToWords2 extends AppCompatActivity {

    TextView Question;
    TextInputEditText Answer;
    Button Submit, Check;
    ImageView ImgAnimation;
    static boolean flag=true;
    static int score = 0;
    static HashMap<Integer, String> words = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_to_words);
        dataset();
        Question = findViewById(R.id.textViewQuestion);
        Answer = findViewById(R.id.textInputEditTextAnswer);
        Submit = findViewById(R.id.buttonSubmit);
        Check = findViewById(R.id.buttonCheck);
        ImgAnimation = findViewById(R.id.imageViewAnimatorNW);

        nextQuestion();
        Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Answer.setText(WordAnswer(Integer.parseInt(Question.getText().toString())));
                Answer.setTextColor(0xFF00FF00);
                flag=false;
                Submit.setText("NEXT");
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(WordAnswer(Integer.parseInt(Question.getText().toString())).equalsIgnoreCase(Answer.getText().toString())) {
                    if(flag) {
                        score++;
                        CorrectAnimation();
                    }
                    nextQuestion();
                }
                else
                    WrongAnimation();
            }
        });


    }

    public void dataset()
    {
        words.put(0, "");
        words.put(1, "One");
        words.put(2, "Two");
        words.put(3, "Three");
        words.put(4, "Four");
        words.put(5, "Five");
        words.put(6, "Six");
        words.put(7, "Seven");
        words.put(8, "Eight");
        words.put(9, "Nine");
        words.put(50,"Fifty");
        words.put(60, "Sixty");
        words.put(70, "Seventy");
        words.put(80, "Eighty");
        words.put(90, "Ninety");
        words.put(100, "Hundred");
    }

    public String WordAnswer(int num)
    {
        if(100 == num)
            return words.get(num);
        int tens = num/10;
        String ans = words.get(tens*10) + " " + words.get(num%10);
        return ans;
    }

    public void nextQuestion()
    {
        Random r = new Random();
        int next = r.nextInt(50) + 51;
        Question.setText(Integer.toString(next));
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



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,NumberDashboard2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        super.onBackPressed();
    }
}
