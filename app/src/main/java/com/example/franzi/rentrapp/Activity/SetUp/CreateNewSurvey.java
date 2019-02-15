package com.example.franzi.rentrapp.Activity.SetUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.franzi.rentrapp.Activity.Menue;
import com.example.franzi.rentrapp.R;
import com.example.franzi.rentrapp.Model.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Map;

public class CreateNewSurvey extends AppCompatActivity implements View.OnClickListener {

    private String companyName;
    private String projectName;
    private String systemType;
    private String systemStatus;

    private ArrayList<String> projectInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_survey);

        projectInfoList = new ArrayList<String>();

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


        //Set On Click Listeners for buttons
        Button btn = (Button) findViewById(R.id.btnCreateNewSurvey);
        Button btn2 = (Button) findViewById(R.id.btnCreateNewS2);

        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            //Weiter Button
            case R.id.btnCreateNewSurvey:
                Intent intent1 = new Intent(this, CreateNewSurvey2.class);
                getUserInput();
                intent1.putStringArrayListExtra("projectInfoList", projectInfoList);
                startActivity(intent1);
                this.finish();
                break;


            //Menü Button --> Ins Hauptmenü
            case R.id.btnCreateNewS2:
                Intent intent2 = new Intent(this, Menue.class);
                startActivity(intent2);
                this.finish();
                break;

            default: break;
        }


    }

    public void getUserInput (){


        //Get User Input
        final EditText iNewSurvey1 = (EditText)findViewById(R.id.iNewSurvey1);
        final EditText iNewSurvey2 = (EditText) findViewById(R.id.iNewSurvey2);
        final Spinner iNewSurvey3 = (Spinner) findViewById(R.id.iNewSurvey3);
        final Spinner iNewSurvey4 = (Spinner) findViewById(R.id.iNewSurvey4);

        companyName = iNewSurvey1.getText().toString();
        projectName = iNewSurvey2.getText().toString();
        systemType = iNewSurvey3.getSelectedItem().toString();
        systemStatus = iNewSurvey4.getSelectedItem().toString();


        //Check if everything is filled out

        if(filledOutCompletely()!=true){
            Toast.makeText(getApplicationContext(),"@string/notFilledOut",Toast.LENGTH_LONG);
            return;
        }

        //Add to project info list

        projectInfoList.add(companyName);
        projectInfoList.add(projectName);
        projectInfoList.add(systemType);
        projectInfoList.add(systemStatus);

    }

    private boolean filledOutCompletely() {
        if(companyName.equals("")
                |projectName.equals("")
                |systemType.equals("")
                |systemStatus.equals("")
        ){
            return false;
        } else{
            return true;
        }
    }

    public static void saveSurveyInDatabase (Survey newSurvey){


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getReference();

        //To-Do SurveyCode

        String key = mRef.child("Survey").push().getKey();
        newSurvey.setSurveyCode(key);

        //Transform Input into HashMap of Survey and add to database
        Map<String, Object> surveyMap = newSurvey.toMap();

        mRef.child("Survey").child(key).setValue(surveyMap);

    }



}
