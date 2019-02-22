package com.example.franzi.rentrapp.Activity.Execution;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.franzi.rentrapp.Activity.Menue;
import com.example.franzi.rentrapp.R;

public class SpecificSurveyDone extends AppCompatActivity implements View.OnClickListener {

    Button btnMenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_survey_done);

        Button btnMenue = findViewById(R.id.btn_Menue);
        btnMenue.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Menue.class);
        startActivity(intent);
        this.finish();
    }
}
