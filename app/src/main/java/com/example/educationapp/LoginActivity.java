package com.example.educationapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    ImageView Backbutton;
    private FirebaseAuth mAuth;
    Button btn;
    EditText email;
    EditText password;
    String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TAG = "LOGIN";

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser()!=null){
            //String uid = mAuth.getCurrentUser().getUid();
            Intent intent = new Intent(getApplicationContext(), MainDashboard.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }

        Backbutton=findViewById(R.id.backBtn);
        btn = findViewById(R.id.button);
        email = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);

        Backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,Dashboard.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });


    }

    private void userLogin() {
        String mEmail=email.getText().toString().trim();
        String mPassword =password.getText().toString().trim();

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


        mAuth.signInWithEmailAndPassword(mEmail, mPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG,"SignInWithEmail:Success",task.getException());
                            Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_SHORT).show();
                            //String uid = mAuth.getCurrentUser().getUid();
                            Intent intent = new Intent(getApplicationContext(), MainDashboard.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), Objects.requireNonNull(task.getException()).getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
