package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PutInPlace extends AppCompatActivity {

    Button Option1, Option2, Option3, Option4, Option5, Option6, Reset, Submit;
    TextInputEditText Answer[] = new TextInputEditText[6];
    char startingAlphabet='A';
    int clicks=0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_in_place);

        generateQues();
        Submit = findViewById(R.id.button11);
        Reset = findViewById(R.id.buttonReset);

        Answer[0] = findViewById(R.id.textInputEditText1);
        Answer[1] = findViewById(R.id.textInputEditText2);
        Answer[2] = findViewById(R.id.textInputEditText3);
        Answer[3] = findViewById(R.id.textInputEditText4);
        Answer[4] = findViewById(R.id.textInputEditText5);
        Answer[5] = findViewById(R.id.textInputEditText6);


        Option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(clicks!=6)
                Answer[clicks++].setText(Option1.getText().toString());
                Option1.setVisibility(View.INVISIBLE);
            }
        });

        Option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(clicks!=6)
                Answer[clicks++].setText(Option2.getText().toString());
                Option2.setVisibility(View.INVISIBLE);
            }
        });

        Option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(clicks!=6)
                Answer[clicks++].setText(Option3.getText().toString());
                Option3.setVisibility(View.INVISIBLE);
            }
        });

        Option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(clicks!=6)
                Answer[clicks++].setText(Option4.getText().toString());
                Option4.setVisibility(View.INVISIBLE);
            }
        });

        Option5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(clicks!=6)
                Answer[clicks++].setText(Option5.getText().toString());
                Option5.setVisibility(View.INVISIBLE);
            }
        });

        Option6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(clicks!=6)
                Answer[clicks++].setText(Option6.getText().toString());
                Option6.setVisibility(View.INVISIBLE);
            }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                visible();
                for(int i=0; i<6; i++)
                    Answer[i].setText("");
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = true;

                try {

                    for (int i = 0; i < 6; i++) {

                        if (Answer[i].getText().toString().charAt(0) == startingAlphabet + i)
                            continue;
                        else {
                            Toast.makeText(PutInPlace.this, "WRONG ANSWER, RETRY", Toast.LENGTH_SHORT).show();
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        Toast.makeText(PutInPlace.this, "CORRECT ANSWER", Toast.LENGTH_SHORT).show();
                        generateQues();
                        visible();
                        for(int i=0; i<6; i++)
                            Answer[i].setText("");

                    }

                } catch (Exception e)
                {
                    Toast.makeText(PutInPlace.this, "COMPLETE THE ANSWER", Toast.LENGTH_SHORT).show();;
                }
            }
        });

    }

    private void generateQues() {
        int randomInteger = new Random().nextInt(21) + 65;
        startingAlphabet = (char) randomInteger;
        Character[] arr = { startingAlphabet, (char) (startingAlphabet+1), (char) (startingAlphabet + 2), (char) (startingAlphabet+3), (char) (startingAlphabet + 4), (char) (startingAlphabet+5)};
        shuffle(arr);

        Option1 = findViewById(R.id.button2);
        Option1.setText(arr[0].toString());
        Option2 = findViewById(R.id.button4);
        Option2.setText(arr[1].toString());
        Option3 = findViewById(R.id.button3);
        Option3.setText(arr[2].toString());
        Option4 = findViewById(R.id.button9);
        Option4.setText(arr[3].toString());
        Option5 = findViewById(R.id.button7);
        Option5.setText(arr[4].toString());
        Option6 = findViewById(R.id.button10);
        Option6.setText(arr[5].toString());
    }


    private void shuffle(Character[] intArray) {
        List<Character> intList = Arrays.asList(intArray);
        Collections.shuffle(intList);
        intList.toArray(intArray);
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
