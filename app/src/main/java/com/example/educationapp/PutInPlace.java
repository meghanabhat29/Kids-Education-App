package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class PutInPlace extends AppCompatActivity {

    Button Option1, Option2, Option3, Option4, Option5, Option6, Reset, Submit;
    TextView Question;
    TextInputEditText Answer[] = new TextInputEditText[5];
    int clicks=0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_in_place);

        Option1 = findViewById(R.id.button2);
        Option2 = findViewById(R.id.button4);
        Option3 = findViewById(R.id.button3);
        Option4 = findViewById(R.id.button9);
        Option5 = findViewById(R.id.button7);
        Option6 = findViewById(R.id.button10);
        Submit = findViewById(R.id.button11);
        Reset = findViewById(R.id.buttonReset);

        Question = findViewById(R.id.textView11);

        Answer[0] = findViewById(R.id.textInputEditText2);
        Answer[1] = findViewById(R.id.textInputEditText3);
        Answer[2] = findViewById(R.id.textInputEditText4);
        Answer[3] = findViewById(R.id.textInputEditText5);
        Answer[4] = findViewById(R.id.textInputEditText6);

        final char startingAlphabet = Question.getText().toString().charAt(0);

        Option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(clicks!=5)
                Answer[clicks++].setText(Option1.getText().toString());
                Option1.setVisibility(View.INVISIBLE);
            }
        });

        Option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(clicks!=5)
                Answer[clicks++].setText(Option2.getText().toString());
                Option2.setVisibility(View.INVISIBLE);
            }
        });

        Option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(clicks!=5)
                Answer[clicks++].setText(Option3.getText().toString());
                Option3.setVisibility(View.INVISIBLE);
            }
        });

        Option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(clicks!=5)
                Answer[clicks++].setText(Option4.getText().toString());
                Option4.setVisibility(View.INVISIBLE);
            }
        });

        Option5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(clicks!=5)
                Answer[clicks++].setText(Option5.getText().toString());
                Option5.setVisibility(View.INVISIBLE);
            }
        });

        Option6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(clicks!=5)
                Answer[clicks++].setText(Option6.getText().toString());
                Option6.setVisibility(View.INVISIBLE);
            }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                visible();
                for(int i=0; i<5; i++)
                    Answer[i].setText("");
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                boolean flag=true;
                for(int i=0; i<5; i++)
                {
                    if(Answer[i].getText().toString().charAt(0)==startingAlphabet+i+1)
                        continue;
                    else {
                        Toast.makeText(PutInPlace.this, "WRONG ANSWER", Toast.LENGTH_SHORT).show();
                        flag=false;
                        break;
                    }
                }
                if(flag)
                    Toast.makeText(PutInPlace.this, "CORRECT ANSWER", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void visible()
    {
        Option1.setVisibility(View.VISIBLE);
        Option2.setVisibility(View.VISIBLE);
        Option3.setVisibility(View.VISIBLE);
        Option4.setVisibility(View.VISIBLE);
        Option5.setVisibility(View.VISIBLE);
        Option6.setVisibility(View.VISIBLE);
        clicks=0;
    }
}
