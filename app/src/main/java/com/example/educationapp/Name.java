package com.example.educationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class Name extends AppCompatActivity {

    TextInputEditText name;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    ImageButton next;
    String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser()==null){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }

        name = findViewById(R.id.name);
        initFirestore();

        next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name = name.getText().toString().trim();
                addName(user_name);
            }
        });

    }

    private void addName(String name) {

        Map<String,String> userMap = new HashMap<>();
        userMap.put("Name",user_name);

        db.collection("users")
                .document(mAuth.getCurrentUser().getUid())
                .set(userMap, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("FIRESTORE", "Success");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("FIRESTORE:", "Failure "+e.toString());
                    }
                });

        Intent intent = new Intent(getApplicationContext(), MainDashboard.class);
        startActivity(intent);
        finish();

    }

    private void initFirestore() {
        db = FirebaseFirestore.getInstance();
    }
}
