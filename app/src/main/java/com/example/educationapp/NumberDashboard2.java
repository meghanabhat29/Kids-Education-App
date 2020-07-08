package com.example.educationapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
<<<<<<< HEAD

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
=======
>>>>>>> 17e4703955aa9b113eaef763dc2524e89ee33539

public class NumberDashboard2 extends AppCompatActivity {

    CardView speak, put, caps, revise;
    TextView name;
    private FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_dashboard2);

        revise = findViewById(R.id.revise);
        speak = findViewById(R.id.speak);
        put = findViewById(R.id.inplace);
        caps = findViewById(R.id.caps);
<<<<<<< HEAD
        name = findViewById(R.id.user_nameN2);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        uid = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("users").document("uid");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                String res = (String) documentSnapshot.getString("Name");
                name.setText("ID: "+ uid.substring(0,5));
            }
        });
=======
        TextView scoreSpeak = findViewById(R.id.scoreNumber2Speak);
        TextView scorePIP = findViewById(R.id.scoreNumber2PIP);
        TextView scoreNTW = findViewById(R.id.scoreNumber2NTW);
>>>>>>> 17e4703955aa9b113eaef763dc2524e89ee33539

        revise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NumberRevision2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SpeakAloudNumber2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PutInPlaceNumber2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        caps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NumberToWords2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        scoreSpeak.setText(Integer.toString(SpeakAloudNumber2.score));
        scorePIP.setText(Integer.toString(PutInPlaceNumber2.score));
        scoreNTW.setText(Integer.toString(NumberToWords2.score));

    }
}
