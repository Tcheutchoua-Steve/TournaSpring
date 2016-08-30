package com.steeboon.dev.tournaspring.ui;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.steeboon.dev.tournaspring.NetworkActivity;
import com.steeboon.dev.tournaspring.R;

public class SelectTournament extends AppCompatActivity implements View.OnClickListener{

    private Button comp1 ;
    private Button comp2 ;
    private Button comp3 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_tournament);

        comp1 = (Button)findViewById(R.id.java_keep_alive);
        comp2 = (Button)findViewById(R.id.mastering_moodle);
        comp3 = (Button)findViewById(R.id.general_knowledge);

        comp1.setOnClickListener(this);
        comp2.setOnClickListener(this);
        comp3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(comp1)){
            Toast.makeText(SelectTournament.this, "Questions Not saved in competition",
                    Toast.LENGTH_LONG).show();
        }
        if(v.equals(comp2)){
            Toast.makeText(SelectTournament.this, "Questions Not saved in competition",
                    Toast.LENGTH_LONG).show();
        }
        if(v.equals(comp3)){
            String servicestring = Context.DOWNLOAD_SERVICE;
            DownloadManager downloadmanager;
            downloadmanager = (DownloadManager) getSystemService(servicestring);
            Uri uri = Uri
                    .parse("http://192.168.1.101/fyp/questions.xml");
            DownloadManager.Request request = new DownloadManager.Request(uri);
            Long reference = downloadmanager.enqueue(request);
            Toast.makeText(SelectTournament.this, "Getting Questions",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, NetworkActivity.class);
            startActivity(intent);
        }
    }
}
