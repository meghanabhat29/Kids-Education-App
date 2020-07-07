package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class TimeReading extends AppCompatActivity {

    ClockView clock;
    TimePicker picker;
    Button btnGet;

    public static int hour, minute;
    public static String am_pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_reading);

        clock = findViewById(R.id.clock);

        picker=(TimePicker)findViewById(R.id.timePicker1);
        picker.setIs24HourView(true);
        btnGet=(Button)findViewById(R.id.button1);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= 23 ){
                    hour = picker.getHour();
                    minute = picker.getMinute();
                }
                else{
                    hour = picker.getCurrentHour();
                    minute = picker.getCurrentMinute();
                }
                if(hour > 12) {
                    am_pm = "PM";
                    hour = hour - 12;
                }
                else
                {
                    am_pm="AM";
                }

                Toast.makeText(getApplicationContext(),"Selected Date: "+ hour +":"+ minute+" "+am_pm, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), TimeReading.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            }
        });

    }


}
