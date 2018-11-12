package com.example.franzi.rentrapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CreateNewSurvey extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_survey);

        //Init Spinner 1

        Spinner dropdown = findViewById(R.id.iNewSurvey3);
        String[] items = new String[]{"ERP", "CRM", "Andere"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        //Init Spinner 2

        Spinner dropdown2 = findViewById(R.id.iNewSurvey4);
        String[] items2 = new String[]{"Neueinführung", "Bestehendes System"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown2.setAdapter(adapter2);
    }

    @Override
    public void onClick(View v) {

        //Get User Input
        final EditText iNewSurvey1 = (EditText)findViewById(R.id.iNewSurvey1);
        final EditText iNewSurvey2 = (EditText) findViewById(R.id.iNewSurvey2);
        final Spinner iNewSurvey3 = (Spinner) findViewById(R.id.iNewSurvey3);
        final Spinner iNewSurvey4 = (Spinner) findViewById(R.id.iNewSurvey4);

        String companyName = iNewSurvey1.getText().toString();
        String projectName = iNewSurvey2.getText().toString();
        String systemType = iNewSurvey3.getSelectedItem().toString();
        String systemStatus = iNewSurvey4.getSelectedItem().toString();
        String surveyCode;

        Question[] questions = getQuestions(systemStatus);

        //To-Do Config
        boolean[] config = {true,false};

        //To-Do Create Survey Code & Display it
        surveyCode = "TEST00";

        TextView tvSurveyCode = (TextView) findViewById(R.id.tvNewSurvey6);
        tvSurveyCode.setText(surveyCode);

        //Create new instance of Survey

        Survey newSurvey = new Survey(surveyCode,companyName,projectName,systemType,systemStatus,questions,config);

        //Survey Objectinstanz in Datenbank abspeichern

        //Benachrictigung über erfolgreiche Speicherung der Umfrage


    }

    public static Question[] getQuestions(String systemStatus){

        Question[] questions;

        //Zugriff auf Datenbank Fragebogen


        return questions;
    }
}
