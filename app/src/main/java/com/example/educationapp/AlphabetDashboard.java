package com.example.educationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AlphabetDashboard extends AppCompatActivity
{
    Button mTest, mPractice;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet_dashboard);
        mTest = findViewById(R.id.buttonTest);
        mPractice = findViewById(R.id.buttonPractice);

        mTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Intent intent = new Intent(AlphabetDashboard.this, Question.class);
                startActivity(intent);
            }
        });

        mPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlphabetDashboard.this, AlphabetPractice.class);
                startActivity(intent);
            }
        });
    }
}

