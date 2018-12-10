package com.example.franzi.rentrapp.Activity.SetUp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.franzi.rentrapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.franzi.rentrapp.Model.*;

import java.util.ArrayList;

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
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);

        Button btn = (Button) findViewById(R.id.btnCreateNewS);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        addSurvey();
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
        String surveyCode;

        //  Question[] questions = getQuestions(systemStatus);

        //To-Do Config
        boolean[] config = {true,false};

        //To-Do Create Survey Code & Display it
        surveyCode = "TEST00";

        TextView tvSurveyCode = (TextView) findViewById(R.id.tvNewSurvey6);
        tvSurveyCode.setText(surveyCode);

        //Create new instance of Survey

        Survey newSurvey = new Survey(surveyCode,companyName,projectName,systemType,systemStatus,0);


        //Survey Objectinstanz in Datenbank abspeichern

        DatabaseReference surveyDatabase = FirebaseDatabase.getInstance().getReference("Surveys");

        String id = surveyDatabase.push().getKey();

        surveyDatabase.child(id).setValue(newSurvey);

        Toast.makeText(this, "Umfrage erstellt", Toast.LENGTH_LONG);

    }
   /* public static Question[] getQuestions(String systemStatus){

        Question[] questions = new Question[6];

        //Zugriff auf Datenbank Fragebogen

        questions[0] = new Question(1, "Ich interessiere mich für Computer und IT.", 1);
        questions[1] = new Question(1, "Ich bin gegenüber neuen Technologien positiv eingestellt.Ich bin gegenüber neuen Technologien positiv eingestellt.", 1);
        questions[2] = new Question(1, "Ich bin gegenüber der Einführung dieses Systems positiv eingestellt.", 1);
        questions[3] = new Question(1, "Ich fühle mich in der Lage das System zielgerichtet zu nutzen.", 1);
        questions[4] = new Question(1, "Meine erworbenen Kompetenzen kann ich im neuen System nutzen.", 1);
        questions[5] = new Question(1, "Ich habe mit dem System bereits Erfahrungen gesammelt.", 1);
        return questions;
    }*/
}
