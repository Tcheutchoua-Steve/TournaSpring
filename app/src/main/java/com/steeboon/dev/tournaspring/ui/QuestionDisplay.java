package com.steeboon.dev.tournaspring.ui;

import android.content.Intent;
import android.os.Bundle;
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

    // The bundle that will help in containing the parceable questions
    private static Bundle  question_bundle ;
    //The current question number that is being answered.
    private static int current_question_number = 0 ;

   // private RadioButton radio_answer4;
    private QuestionList  allquestions ;
    private int number_of_questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_display_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get the currect question number from the previous activity
        Intent in = getIntent();
        Bundle bundle = in.getExtras();

        if(bundle.get("lastQuestion") != null) {
            current_question_number = (int) bundle.get("lastQuestion");
        }
        radio_group_question = (RadioGroup)findViewById(R.id.radioGroupQuestion);
        radio_question = (TextView)findViewById(R.id.radioQuestion);
        radio_answer1 = (RadioButton)findViewById(R.id.radioAnswer1);
        radio_answer2 = (RadioButton)findViewById(R.id.radioAnswer2);
        radio_answer3 = (RadioButton)findViewById(R.id.radioAnswer3);
        //radio_answer4 = (RadioButton)findViewById(R.id.radioAnswer4);



        button_prev = (Button)findViewById(R.id.prevQuestion);
        button_next = (Button)findViewById(R.id.nextQuestion);

        //Set Listeners to the buttons. Their logic is found in the
        // onClick method thanks to the onClickinterface we are implementing


        question_bundle = getIntent().getExtras(); // Get the intent's extras

        allquestions = question_bundle.getParcelable("questionList");

        Log.i("arrow_crr_question",String.valueOf(current_question_number));
        Log.i("allquestions",allquestions.toString());


        allquestions = question_bundle.getParcelable("questionList");

        if(this.current_question_number <= 0){
            button_prev.setEnabled(false);
        }
        else
        if (this.current_question_number >= allquestions.size() - 1){
            button_next.setEnabled(false);
        }

        button_next.setOnClickListener(this);
        button_prev.setOnClickListener(this);


            Log.i("num_questions",String.valueOf(allquestions.size()));
        Log.i("allquestions",allquestions.toString());
        radio_question.setText(allquestions.get(current_question_number).getQuestion_asked());
        radio_answer1.setText(allquestions.get(current_question_number).getAnswer1());
        radio_answer2.setText(allquestions.get(current_question_number).getAnswer2());
        radio_answer3.setText(allquestions.get(current_question_number).getAnswer3());


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
            Intent intent = new Intent(this, this.getClass());
            intent.putExtras(question_bundle);
            intent.putExtra("lastQuestion",current_question_number +1);
            Log.i("arrow","Next button has been clicked");
            startActivity(intent);

        }
        else if (v.equals(button_prev)){
            Intent intent = new Intent(this, this.getClass());
            intent.putExtras(question_bundle);
            intent.putExtra("lastQuestion",current_question_number -1);
            Log.i("arror","Prev button has been clicked");
            startActivity(intent);
        }

    }

    public int verifyQuestionNumber(int number, int size ){

        // If someone clicks previous while in the first question
        if(number < 0 )
            return 1 ;
        // if someone clicks next after we are on the last question
        else if (number > size )
            return 1 ;
        else
            return number ;


    }
}
