package com.example.franzi.rentrapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CreateNewSurvey extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_survey);

        //Init Spinner 1
        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner1_SysTyp);
        //create a list of items for the spinner.
        String[] items = new String[]{"ERP", "CRM", "Andere"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        //Init Spinner 2
        //get the spinner from the xml.
        Spinner dropdown2 = findViewById(R.id.spinner2_SysStat);
        //create a list of items for the spinner.
        String[] items2 = new String[]{"Neueinführung", "Bestehendes System"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter2);
    }

    @Override
    public void onClick(View v) {

        String surveyCode = "0000Test";
        String companyName;
        String projectName;
        String systemType;
        String systemStatus;

        //Survey newSurvey = new Survey();
    }
}
