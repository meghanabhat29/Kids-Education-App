package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SmallBigLetter extends AppCompatActivity {

    TextView Small, Capital;
    ImageButton Tick, Cross;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_big_letter);

        Small = findViewById(R.id.textViewSmall);
        Capital = findViewById(R.id.textViewCapital);
        Tick = findViewById(R.id.imageButtonTick);
        Cross = findViewById(R.id.imageButtonCross);

        Tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!Small.getText().toString().equals(Capital.getText().toString().toLowerCase()))
                    Toast.makeText(SmallBigLetter.this,"Wrong Answer",Toast.LENGTH_SHORT).show();
                else
                {
                    Toast.makeText(SmallBigLetter.this,"Correct Answer",Toast.LENGTH_SHORT).show();
                    nextQuestion();
                }
            }
        });

        Cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if((Small.getText().toString().equals(Capital.getText().toString().toLowerCase())))
                    Toast.makeText(SmallBigLetter.this,"Wrong Answer",Toast.LENGTH_SHORT).show();
                else
                {
                    Toast.makeText(SmallBigLetter.this,"Correct Answer",Toast.LENGTH_SHORT).show();
                    nextQuestion();
                }
            }
        });

    }

    private void nextQuestion()
    {

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();

        Random random = new Random();
        int choice = random.nextInt(10);

        char code = alphabet.charAt(random.nextInt(25));
        char code2 = alphabet.charAt(random.nextInt(25));
        Small.setText(Character.toString(code));
        if(choice%2==0)
            Capital.setText(Character.toString(code).toUpperCase());
        else
            Capital.setText(Character.toString(code2).toUpperCase());

    }
}
