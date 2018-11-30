package com.example.franzi.rentrapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateNewSurvey extends AppCompatActivity implements View.OnClickListener {

    Survey newSurvey;
    String surveyCode="";

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
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);

        Button btn = (Button) findViewById(R.id.btnCreateNewS);
        Button btn2 = (Button) findViewById(R.id.btnCreateNewS2);

        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            //Umfrage erstellen Button
            case R.id.btnCreateNewS:
                addSurvey();
                break;


            //Menü Button --> Ins Hauptmenü
            case R.id.btnCreateNewS2:
                Intent intent1 = new Intent(this, Menue.class);
                intent1.putExtra("SurveyCode",surveyCode);
                startActivity(intent1);
                this.finish();
                break;

            default: break;
        }


    }

    public void addSurvey (){

        //Get User Input
        final EditText iNewSurvey1 = (EditText)findViewById(R.id.iNewSurvey1);
        final EditText iNewSurvey2 = (EditText) findViewById(R.id.iNewSurvey2);
        final Spinner iNewSurvey3 = (Spinner) findViewById(R.id.iNewSurvey3);
        final Spinner iNewSurvey4 = (Spinner) findViewById(R.id.iNewSurvey4);

        String companyName = iNewSurvey1.getText().toString();
        String projectName = iNewSurvey2.getText().toString();
        String systemType = iNewSurvey3.getSelectedItem().toString();
        String systemStatus = iNewSurvey4.getSelectedItem().toString();


        //  Question[] questions = getQuestions(systemStatus);

        //To-Do Config
        boolean[] config = {true,false};



        //Create new instance of Survey

        newSurvey = new Survey(surveyCode,companyName,projectName,systemType,systemStatus,0);



        //Save in database

        saveSurveyInDatabase();

        //To-Do Create Survey Code & Display it
        surveyCode = newSurvey.getSurveyCode();

        TextView tvSurveyCode = (TextView) findViewById(R.id.tvNewSurvey6);
        tvSurveyCode.setText(surveyCode);

         Toast.makeText(this, "Umfrage erfolgreich erstellt", Toast.LENGTH_LONG).show();
    }

    private void saveSurveyInDatabase (){

        //Survey Objectinstanz in Datenbank abspeichern

        DatabaseReference surveyDatabase = FirebaseDatabase.getInstance().getReference();

        //To-Do SurveyCode

        String key = surveyDatabase.child("Survey").push().getKey();
        newSurvey.setSurveyCode(key);

        //Transform Input into HashMap of Survey and add to database
        Map<String,Object> postValues = newSurvey.toMap();

      //Map<String,Object> surveyUpdate = new HashMap<>();
      //surveyUpdate.put(key,surveyUpdate);

        surveyDatabase.child("Survey").child(key).setValue(postValues);
    }

}
