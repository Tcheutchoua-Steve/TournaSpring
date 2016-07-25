package com.steeboon.dev.tournaspring.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.steeboon.dev.tournaspring.R;
import com.steeboon.dev.tournaspring.util.QuestionList;


public class QuestionDisplay extends AppCompatActivity implements View.OnClickListener {

    // Creating questions view variables
    private RadioGroup radio_group_question ;
    private TextView    radio_question;
    private RadioButton radio_answer1;
    private RadioButton radio_answer2;
    private RadioButton radio_answer3;

    //The Previous and Next Questin buttons
    private Button button_next ;
    private Button button_prev ;

    private TextView time_left ;

    // The bundle that will help in containing the parceable questions
    private static Bundle  question_bundle ;
    //The current question number that is being answered.
    private static int current_question_number = 0 ;

   // private RadioButton radio_answer4;
    private QuestionList  allquestions ;
    private int number_of_questions;

    // Create Object to be able to handle timer efficienctly
    private CountDownTimer timer ;

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

        time_left = (TextView)findViewById(R.id.timer);



        button_prev = (Button)findViewById(R.id.prevQuestion);
        button_next = (Button)findViewById(R.id.nextQuestion);



        question_bundle = getIntent().getExtras(); // Get the intent's extras

        // Get allParsed questions from the previous activity
        allquestions = question_bundle.getParcelable("questionList");

        //Set Listeners to the buttons. Their logic is found in the
        // onClick method thanks to the onClickinterface we are implementing
        button_next.setOnClickListener(this);
        button_prev.setOnClickListener(this);


        displayQuestions(0);


      /*  Floatin (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }


    @Override
    public void onClick(View v) {
        if (v.equals(button_next)){
            timer.cancel();
            displayQuestions(current_question_number + 1);
        }
        else if (v.equals(button_prev)){
            timer.cancel();
            displayQuestions(current_question_number - 1);
        }

    }

    public void displayQuestions(int n){

        setTimer(40000,1000);

        this.current_question_number = n;

        // disable previous button if we are on the first question.
        if(current_question_number <= 0){
            button_prev.setEnabled(false);
        }
        else {
            button_prev.setEnabled(true);
        }

        // Disable next button if we are on the last question
        if (current_question_number >= allquestions.size() - 1){
            button_next.setEnabled(false);
        }
        else {button_next.setEnabled(true);}

        radio_question.setText(allquestions.get(current_question_number).getQuestion_asked());
        radio_answer1.setText(allquestions.get(current_question_number).getAnswer1());
        radio_answer2.setText(allquestions.get(current_question_number).getAnswer2());
        radio_answer3.setText(allquestions.get(current_question_number).getAnswer3());
    }

    public void setTimer(final long millisInFuture, long CountDownInterval){
        timer =  new  CountDownTimer(millisInFuture, CountDownInterval) {

            public void onTick(long millisUntilFinished) {
                // color of text set to orange when less than 30 seconds
                if (millisUntilFinished > 10000 && millisUntilFinished < 30000){
                    time_left.setTextColor(Color.rgb(255, 165, 0));
                    time_left.setText(Long.toString(millisUntilFinished / 1000));
                }
                // color set to red when less than 10 seconds
                else if (millisUntilFinished <=10000 ){
                    time_left.setTextColor(Color.rgb(255, 0, 0));
                    time_left.setText(Long.toString(millisUntilFinished / 1000));
                }
                else {
                    time_left.setText(Long.toString(millisUntilFinished / 1000));
                }

            }

            public void onFinish() {
                time_left.setText("done!");

                //move to next question
                button_next.performClick();
            }

        }.start();
    }
}
