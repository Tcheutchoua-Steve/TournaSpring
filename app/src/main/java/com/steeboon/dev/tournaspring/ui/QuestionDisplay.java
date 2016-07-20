package com.steeboon.dev.tournaspring.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.steeboon.dev.tournaspring.R;

public class QuestionDisplay extends AppCompatActivity {

    // Creating questions view variables
    private RadioGroup radioQuestion ;
    private RadioButton radioAnswer1;
    private RadioButton radioAnswer2;
    private RadioButton radioAnswer3;
    private RadioButton radioAnswer4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_display_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        radioQuestion = (RadioGroup)findViewById(R.id.radioQuestion);
        radioAnswer1 = (RadioButton)findViewById(R.id.radioAnswer1);
        radioAnswer2 = (RadioButton)findViewById(R.id.radioAnswer2);
        radioAnswer3 = (RadioButton)findViewById(R.id.radioAnswer3);
        radioAnswer4 = (RadioButton)findViewById(R.id.radioAnswer4);


        






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
