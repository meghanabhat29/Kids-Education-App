package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Random;

public class NumberToWords2 extends AppCompatActivity {

    TextView Question;
    TextInputEditText Answer;
    Button Submit, Check;
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

        nextQuestion();
        Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Answer.setText(WordAnswer(Integer.parseInt(Question.getText().toString())));
                Answer.setTextColor(0xFF00FF00);
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(WordAnswer(Integer.parseInt(Question.getText().toString())).equalsIgnoreCase(Answer.getText().toString())) {
                    Toast.makeText(NumberToWords2.this, "CORRECT ANSWER", Toast.LENGTH_SHORT).show();
                    nextQuestion();
                }
                else
                    Toast.makeText(NumberToWords2.this, "WRONG ANSWER, RETRY", Toast.LENGTH_SHORT).show();

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
    }
}
