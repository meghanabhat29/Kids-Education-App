package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    TextView registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton=findViewById(R.id.loginBtn);
        registration = findViewById(R.id.register);

        //FirebaseAuth.getInstance().signOut();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegistrationPage.class);
                startActivity(intent);
            }
        });

    }
}
