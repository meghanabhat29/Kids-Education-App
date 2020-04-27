package com.example.educationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OneManyDashboard extends AppCompatActivity {
    Button mOneMany, mManyOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onemany_dashboard);
        mOneMany = findViewById(R.id.buttonOneMany);
        mManyOne = findViewById(R.id.buttonManyOne);

        mOneMany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OneManyDashboard.this, OneMany.class);
                startActivity(intent);
            }
        });

        mManyOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OneManyDashboard.this, ManyOne.class);
                startActivity(intent);
            }
        });
    }
}
