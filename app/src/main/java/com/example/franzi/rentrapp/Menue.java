package com.example.franzi.rentrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menue extends AppCompatActivity implements View.OnClickListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menue);

        Button btnCreateSurvey = (Button)findViewById(R.id.btnCreateSurvey);
        btnCreateSurvey.setOnClickListener(this);

        Button btnParticipate = (Button)findViewById(R.id.btnParticipate);
        btnParticipate.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnCreateSurvey:
                Intent intent = new Intent(this, CreateNewSurvey.class);
                startActivity(intent);
                this.finish();
            break;

            case R.id.btnParticipate:
                Intent intent1 = new Intent(this, Start.class);
                startActivity(intent1);
                this.finish();
            break;

            default: break;
        }

    }
}

