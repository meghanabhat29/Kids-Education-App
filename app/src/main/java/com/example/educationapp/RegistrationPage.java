package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationPage extends AppCompatActivity {

    EditText Fname;
    EditText Lname;
    EditText DateofBirth;
    EditText email;
    EditText password;
    Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        Fname=findViewById(R.id.fname);
        Lname = findViewById(R.id.lname);
        DateofBirth = findViewById(R.id.dob);
        email = findViewById(R.id.email);
        password = findViewById(R.id.passwd);
        Register = findViewById(R.id.Signup);

    }
}
