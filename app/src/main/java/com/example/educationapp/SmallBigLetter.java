package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SmallBigLetter extends AppCompatActivity {

    TextView Small, Capital;
    ImageButton Tick, Cross;
    Button Next;
    static int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_big_letter);

        Small = findViewById(R.id.textViewSmall);
        Capital = findViewById(R.id.textViewCapital);
        Tick = findViewById(R.id.imageButtonTick);
        Cross = findViewById(R.id.imageButtonCross);
        Next = findViewById(R.id.buttonNext);

        Tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!Small.getText().toString().equals(Capital.getText().toString().toLowerCase())) {
                    Toast.makeText(SmallBigLetter.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                    Capital.setText(Small.getText().toString().toUpperCase());
                    Capital.setTextColor(0xFF00FF00);
                    Next.setVisibility(View.VISIBLE);
                    Tick.setEnabled(false);
                    Cross.setEnabled(false);
                }
                else
                {
                    score++;
                    Toast.makeText(SmallBigLetter.this,"Correct Answer",Toast.LENGTH_SHORT).show();
                    nextQuestion();
                }
            }
        });

        Cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if((Small.getText().toString().equals(Capital.getText().toString().toLowerCase()))) {
                    Toast.makeText(SmallBigLetter.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                    Capital.setText(Small.getText().toString().toUpperCase());
                    Capital.setTextColor(0xFF00FF00);
                    Next.setVisibility(View.VISIBLE);
                    Tick.setEnabled(false);
                    Cross.setEnabled(false);
                }
                else
                {
                    score++;
                    Toast.makeText(SmallBigLetter.this,"Correct Answer",Toast.LENGTH_SHORT).show();
                    nextQuestion();
                }
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Next.setVisibility(View.INVISIBLE);
                nextQuestion();
                Tick.setEnabled(true);
                Cross.setEnabled(true);
                Capital.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
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
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,Dashboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        super.onBackPressed();
    }
}
