package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TimeReading extends AppCompatActivity {

    ClockView clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_reading);

        clock = findViewById(R.id.clock);

    }
}
