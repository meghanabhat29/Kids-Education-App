package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;

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

public class NumberToWords extends AppCompatActivity {

    TextView Question;
    TextInputEditText Answer;
    Button Submit, Check;
    ImageView ImgAnimation;
    static boolean flag=true;
    static HashMap<Integer, String> words = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_to_words);
        dataset();
        Question = findViewById(R.id.textViewQuestion);
        Answer = findViewById(R.id.textInputEditTextAnswer);
        Submit = findViewById(R.id.buttonSubmit);
        Check = findViewById(R.id.buttonCheck);
        ImgAnimation = findViewById(R.id.imageViewAnimatorNW);

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
                    if(flag)
                        CorrectAnimation();
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
        words.put(10, "Ten");
        words.put(11, "Eleven");
        words.put(12, "Twelve");
        words.put(13, "Thirteen");
        words.put(14, "Fourteen");
        words.put(15, "Fifteen");
        words.put(16, "Sixteen");
        words.put(17, "Seventeen");
        words.put(18, "Eighteen");
        words.put(19, "Nineteen");
        words.put(20, "Twenty");
        words.put(30, "Thirty");
        words.put(40, "Forty");
        words.put(50, "Fifty");
    }

    public String WordAnswer(int num)
    {
        if(num%21 == num)
            return words.get(num);
        int tens = num/10;
        String ans = words.get(tens*10) + " " + words.get(num%10);
        return ans;
    }

    public void nextQuestion()
    {
        Random r = new Random();
        int next = r.nextInt(51);
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
}
