package com.steeboon.dev.tournaspring.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.steeboon.dev.tournaspring.R;
import com.steeboon.dev.tournaspring.util.QuestionList;

public class QuestionDisplay extends AppCompatActivity {

    // Creating questions view variables
    private RadioGroup radio_group_question ;
    private TextView    radio_question;
    private RadioButton radio_answer1;
    private RadioButton radio_answer2;
    private RadioButton radio_answer3;
    private RadioButton radio_answer4;

    private QuestionList  allquestions ;
    private int number_of_questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_display_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        radio_group_question = (RadioGroup)findViewById(R.id.radioGroupQuestion);
        radio_question = (TextView)findViewById(R.id.radioQuestion);
        radio_answer1 = (RadioButton)findViewById(R.id.radioAnswer1);
        radio_answer2 = (RadioButton)findViewById(R.id.radioAnswer2);
        radio_answer3 = (RadioButton)findViewById(R.id.radioAnswer3);
        radio_answer4 = (RadioButton)findViewById(R.id.radioAnswer4);


        Bundle  b = getIntent().getExtras(); // Get the intent's extras

        allquestions = b.getParcelable("questionList");

        Log.i("num_questions",String.valueOf(allquestions.size()));
        Log.i("allquestions",allquestions.toString());


        allquestions = b.getParcelable("questionList");

        Log.i("num_questions",String.valueOf(allquestions.size()));
        Log.i("allquestions",allquestions.toString());
        radio_question.setText(allquestions.get(1).getQuestion_asked());
        radio_answer1.setText(allquestions.get(1).getAnswer1());
        radio_answer2.setText(allquestions.get(1).getAnswer2());
        radio_answer3.setText(allquestions.get(1).getAnswer3());
        


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
