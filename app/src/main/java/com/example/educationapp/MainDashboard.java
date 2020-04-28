package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainDashboard extends AppCompatActivity {

    CardView cAlphabets, cOneMany, cNumber1, cNumber2, cTime, cArticle, cWords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        cAlphabets = findViewById(R.id.cardAlphabets);
        cOneMany = findViewById(R.id.cardOneMany);
        cNumber1 = findViewById(R.id.cardNumber1);
        cNumber2 = findViewById(R.id.cardNumber2);
        cTime = findViewById(R.id.cardTime);
        cArticle = findViewById(R.id.cardArticlle);
        cWords = findViewById(R.id.cardWord);


        cAlphabets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainDashboard.this, Dashboard.class);
                startActivity(intent);
            }
        });

        cOneMany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainDashboard.this, OneManyDashboard.class);
                startActivity(intent);
            }
        });

        cNumber1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainDashboard.this,"Under Development", Toast.LENGTH_SHORT).show();
            }
        });


        cNumber2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainDashboard.this,"Under Development", Toast.LENGTH_SHORT).show();
            }
        });


        cTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainDashboard.this,"Under Development", Toast.LENGTH_SHORT).show();
            }
        });


        cWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainDashboard.this,"Under Development", Toast.LENGTH_SHORT).show();
            }
        });


        cArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainDashboard.this,"Under Development", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
