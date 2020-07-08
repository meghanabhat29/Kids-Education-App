package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NumberDashboard extends AppCompatActivity {

    CardView speak, put, caps, revise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_dashboard);

        revise = findViewById(R.id.revise);
        speak = findViewById(R.id.speak);
        put = findViewById(R.id.inplace);
        caps = findViewById(R.id.caps);

        TextView scoreSpeak = findViewById(R.id.scoreNumber1Speak);
        TextView scorePIP = findViewById(R.id.scoreNumber1PIP);
        TextView scoreNTW = findViewById(R.id.scoreNumber1NTW);

        revise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NumberRevision.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SpeakAloudNumber.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PutInPlaceNumber.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        caps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NumberToWords.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        scoreSpeak.setText(Integer.toString(SpeakAloudNumber.score));
        scorePIP.setText(Integer.toString(PutInPlaceNumber.score));
        scoreNTW.setText(Integer.toString(NumberToWords.score));

    }
}
