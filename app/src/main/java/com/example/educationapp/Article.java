package com.example.educationapp;

import  androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Article extends AppCompatActivity {

    Button Option1, Option2;
    TextView Question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        Option1 = findViewById(R.id.button2);
        Option2 = findViewById(R.id.button4);

        Drawable buttonDrawable1 = Option1.getBackground();
        buttonDrawable1 = DrawableCompat.wrap(buttonDrawable1);

        //QUESTION TO BE SET RANDOMLY FROM THE LIST OF WORDS PROVIDED BY THEM.

        Question = findViewById(R.id.textView2);
        final char startingAlphabet = Question.getText().toString().charAt(0);
        //Exception strings to be added too, as ' || checkException(Question.getText().toString()) '

        // HAVE TO USE HANDLER TO SORT DELAY ISSUE.

        if (isVowel(startingAlphabet)) {
            final Drawable finalButtonDrawable = buttonDrawable1;
            Option1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    DrawableCompat.setTint(finalButtonDrawable, Color.RED);
                    Option1.setBackground(finalButtonDrawable);
                    DrawableCompat.setTint(finalButtonDrawable, Color.GREEN);
                    Option2.setBackground(finalButtonDrawable);
                    Toast.makeText(Article.this, "WRONG ANSWER", Toast.LENGTH_LONG).show();
                    //Thread.sleep(1000);
                    Intent intent = new Intent(getApplicationContext(), Article.class);
                    startActivity(intent);
                    finish();
                }
            });

            Option2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    DrawableCompat.setTint(finalButtonDrawable, Color.RED);
                    Option1.setBackground(finalButtonDrawable);
                    DrawableCompat.setTint(finalButtonDrawable, Color.GREEN);
                    Option2.setBackground(finalButtonDrawable);
                    Toast.makeText(Article.this, "CORRECT ANSWER", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), Article.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
        else {
            final Drawable finalButtonDrawable = buttonDrawable1;
            Option1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    DrawableCompat.setTint(finalButtonDrawable, Color.GREEN);
                    Option1.setBackground(finalButtonDrawable);
                    DrawableCompat.setTint(finalButtonDrawable, Color.RED);
                    Option2.setBackground(finalButtonDrawable);
                    Toast.makeText(Article.this, "CORRECT ANSWER", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), Article.class);
                    startActivity(intent);
                    finish();
                }
            });

            Option2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    DrawableCompat.setTint(finalButtonDrawable, Color.GREEN);
                    Option1.setBackground(finalButtonDrawable);
                    DrawableCompat.setTint(finalButtonDrawable, Color.RED);
                    Option2.setBackground(finalButtonDrawable);
                    Toast.makeText(Article.this, "WRONG ANSWER", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), Article.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    private boolean isVowel(char c) {
        if (c=='a'|| c=='e'|| c=='i'|| c=='o'||c=='u')
            return true;
        return false;
    }
}
