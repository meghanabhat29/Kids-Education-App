package com.example.educationapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class NumberDashboard3 extends AppCompatActivity {

    TextView Question;
    TextInputEditText Answer;
    Button Submit, Check;
    static int z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_dashboard3);
        Question = findViewById(R.id.textViewQuestion);
        Answer = findViewById(R.id.textInputEditTextAnswer);
        Submit = findViewById(R.id.buttonSubmit);
        Check = findViewById(R.id.buttonCheck);
        nextQuestion();

        Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Answer.setText(WordAnswer(z));
                Answer.setTextColor(0xFF00FF00);
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(WordAnswer(z).equalsIgnoreCase(Answer.getText().toString())) {
                    Toast.makeText(NumberDashboard3.this, "CORRECT ANSWER", Toast.LENGTH_SHORT).show();
                    nextQuestion();
                }
                else
                    Toast.makeText(NumberDashboard3.this, "WRONG ANSWER, RETRY", Toast.LENGTH_SHORT).show();

            }
        });


    }


    public String WordAnswer(int num)
    {
        return String.valueOf(num);
    }

    public void nextQuestion()
    {
        Random r = new Random();

        int x, y;

        x = r.nextInt(50) + 1;
        y = r.nextInt(50) + 1;
        z = 0;
        char operator = '?';

        switch (r.nextInt(3)) {
            case 0:
                operator = '+';
                z = x + y;
                break;
            case 1:
                operator = '-';
                z = x - y;
                break;
            case 2:
                operator = '*';
                z = x * y;
                break;
            default:
                operator = '?';
        }

        String next = x +" "+ operator +" "+ y;
        Question.setText(next);
        Answer.setText("");
        Answer.setTextColor(0xFFFFFFFF);
    }
}
