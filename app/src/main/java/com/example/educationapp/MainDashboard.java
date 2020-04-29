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


    }
    public void Alphabetfn(View view) {
        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
        startActivity(intent);
    }
    public void Articlefn(View view) {
        //Intent intent = new Intent(getApplicationContext(), Dashboard.class);
        //startActivity(intent);
        Toast.makeText(MainDashboard.this,"Under Development", Toast.LENGTH_SHORT).show();
    }
    public void Wordfn(View view) {
        //Intent intent = new Intent(getApplicationContext(), Dashboard.class);
        //startActivity(intent);
        Toast.makeText(MainDashboard.this,"Under Development", Toast.LENGTH_SHORT).show();
    }
    public void Singularfn(View view) {
        Intent intent = new Intent(MainDashboard.this, OneManyDashboard.class);
        startActivity(intent);
    }
    public void Numbersfn(View view) {
        //Intent intent = new Intent(getApplicationContext(), Dashboard.class);
        //startActivity(intent);
        Toast.makeText(MainDashboard.this,"Under Development", Toast.LENGTH_SHORT).show();
    }
    public void AdvNumberFn(View view) {
        //Intent intent = new Intent(getApplicationContext(), Dashboard.class);
        //startActivity(intent);
        Toast.makeText(MainDashboard.this,"Under Development", Toast.LENGTH_SHORT).show();
    }
    public void Timefn(View view) {
        //Intent intent = new Intent(getApplicationContext(), Dashboard.class);
        //startActivity(intent);
        Toast.makeText(MainDashboard.this,"Under Development", Toast.LENGTH_SHORT).show();
    }
    public void Furtherfn(View view) {
        //Intent intent = new Intent(getApplicationContext(), Dashboard.class);
        //startActivity(intent);
        Toast.makeText(MainDashboard.this,"Under Development", Toast.LENGTH_SHORT).show();
    }

}
