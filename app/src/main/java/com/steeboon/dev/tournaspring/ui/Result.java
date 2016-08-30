package com.steeboon.dev.tournaspring.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.steeboon.dev.tournaspring.R;

public class Result extends AppCompatActivity {

    private TextView numberOfQuestions ;
    private TextView results ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        numberOfQuestions = (TextView)findViewById(R.id.num_questions);
        results = (TextView)findViewById(R.id.results);
        int mark = getIntent().getIntExtra("answeredQuestions",10);
        int maxQuestions = getIntent().getIntExtra("numberOfQuestions",11);
        numberOfQuestions.setText(" "+maxQuestions);
        results.setText(" " + mark +"/" + maxQuestions);
    }
}
