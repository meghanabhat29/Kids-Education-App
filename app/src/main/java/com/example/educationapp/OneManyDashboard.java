package com.example.educationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class OneManyDashboard extends AppCompatActivity {
    Button mOneMany, mManyOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onemany_dashboard);
        final TextView scoreOneMany = findViewById(R.id.scoreSingular);
        final TextView scoreManyOne = findViewById(R.id.scorePlural);
        mOneMany = findViewById(R.id.buttonOneMany);
        mManyOne = findViewById(R.id.buttonManyOne);

        mOneMany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OneManyDashboard.this, OneMany.class);
                startActivity(intent);
                finish();
            }
        });

        mManyOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OneManyDashboard.this, ManyOne.class);
                startActivity(intent);
                finish();

            }
        });
        scoreOneMany.setText(Integer.toString(OneMany.score));
        scoreManyOne.setText(Integer.toString(ManyOne.score));

    }



}
