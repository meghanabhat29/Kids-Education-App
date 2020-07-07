package com.example.educationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class ManyOne extends AppCompatActivity {

    TextView Question;
    TextInputEditText Answer;
    Button Submit, Check;
    ImageView ImgAnimation;
    static boolean flag = true;
    HashMap<String,String> QNA = new HashMap<>();

    public void questionBank()
    {
        QNA.put("children","child");
        QNA.put("birds","bird");
        QNA.put("plates","plate");
        QNA.put("news","news");
        QNA.put("geese","goose");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        questionBank();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_many_one);

        Question = findViewById(R.id.textViewQuestion);
        Answer = findViewById(R.id.textInputEditTextAnswer);
        Submit = findViewById(R.id.buttonSubmit);
        Check = findViewById(R.id.buttonCheck);
        ImgAnimation = findViewById(R.id.imageViewAnimatorManyOne);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                   String ans = Answer.getText().toString().trim();

                   if(QNA.get(Question.getText().toString()).equalsIgnoreCase(ans))
                   {
                       if(flag)
                            CorrectAnimation();
                       QNA.remove(Question.getText().toString());
                       Answer.setText("");
                       Answer.setTextColor(0xFFFFFFFF);
                       if(QNA.isEmpty())
                           questionBank();

                       for (String name: QNA.keySet())
                            Question.setText(name);
                       Submit.setText("SUBMIT");
                       flag=true;

                   }
                   else
                       WrongAnimation();
            }
        });

        Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Answer.setText(QNA.get(Question.getText().toString()));
                Answer.setTextColor(0xFF00FF00);
                flag=false;
                Submit.setText("NEXT");
            }
        });

    }

    public void CorrectAnimation()
    {
        ImgAnimation.setVisibility(View.VISIBLE);
        ImgAnimation.setImageResource(R.drawable.correctanimation);
        final AnimationDrawable correctAnimation = (AnimationDrawable) ImgAnimation.getDrawable();
        correctAnimation.start();
    }

    public void WrongAnimation()
    {
        ImgAnimation.setVisibility(View.VISIBLE);
        ImgAnimation.setImageResource(R.drawable.wronganimation);
        final AnimationDrawable wrongAnimation = (AnimationDrawable) ImgAnimation.getDrawable();
        wrongAnimation.start();
    }
}
