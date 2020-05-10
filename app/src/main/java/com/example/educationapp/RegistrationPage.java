package com.example.educationapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.Objects;

public class RegistrationPage extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText cpassword;
    TextView login;
    Button Register;
    ImageView Backbutton;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser()!=null){
            Intent intent = new Intent(getApplicationContext(), Name.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }

        progressDialog = new ProgressDialog(this);


        email = findViewById(R.id.email);
        password = findViewById(R.id.passwd);
        cpassword = findViewById(R.id.passwd3);
        Register = findViewById(R.id.Signup);
        Backbutton = findViewById(R.id.back);
        login = findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegistrationPage.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        Backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view==Register)
                    userSignUp();
            }
        });

    }

    private void userSignUp() {
        String mEmail=email.getText().toString().trim();
        String mPassword =password.getText().toString().trim();
        String mConfirmPassword=cpassword.getText().toString().trim();

        if (TextUtils.isEmpty(mEmail)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            email.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(mPassword)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            password.requestFocus();
            return;
        }
        if (mPassword.length()<6){
            password.setError("Minimum length of password should be 6");
            password.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(mConfirmPassword)) {
            Toast.makeText(this, "Please confirm password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!mPassword.equals(mConfirmPassword)) {
            Toast.makeText(this, "Confirm password does not match your password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Signing up...");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(mEmail, mPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(RegistrationPage.this, "Woo-hoo! Successfully registered", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Name.class);
                            startActivity(intent);
                            finish();
                        } else {
                            if (task.getException()instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(RegistrationPage.this, "Email is already registered", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
}
