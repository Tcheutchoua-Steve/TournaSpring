package com.steeboon.dev.tournaspring;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView adminName ;
    TextView competitionName ;
    TextView numberOfQuestions ;
    TextView numberOfCompetitors;
    TextView competitionTime;
    Button proceed ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adminName = (TextView) findViewById(R.id.adminName);
        competitionName = (TextView)findViewById(R.id.competionName);
        numberOfQuestions = (TextView)findViewById(R.id.number_of_question);
        numberOfCompetitors = (TextView)findViewById(R.id.competitor_number);
        competitionTime = (TextView)findViewById(R.id.competition_time);
        proceed = (Button)findViewById(R.id.continue_to_competition);
    }
}
