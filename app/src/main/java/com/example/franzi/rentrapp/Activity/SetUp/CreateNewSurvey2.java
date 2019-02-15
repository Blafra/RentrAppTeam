package com.example.franzi.rentrapp.Activity.SetUp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.franzi.rentrapp.Activity.Menue;
import com.example.franzi.rentrapp.Controller.WriteToDB;
import com.example.franzi.rentrapp.Model.Question;
import com.example.franzi.rentrapp.Model.Survey;
import com.example.franzi.rentrapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CreateNewSurvey2 extends AppCompatActivity implements View.OnClickListener{


    Survey newSurvey;
    String surveyCode="";

    String companyName;
    String projectName;
    String systemType;
    String systemStatus;

    boolean participantAge;
    boolean participantDepartment;
    boolean participantPosition;

    String focus1;
    String focus2;
    String focus3;

    //Variablen für Datenbankabfragen
    final List<String> questionTextList = new ArrayList<String>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mRef = database.getReference();

    //Variable für Zwischenspeicher Kopie
    ClipboardManager cbm;
    ClipData clipData1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_survey2);

        //Get infomation from previous activity
        ArrayList<String> projectInfoList = getIntent().getStringArrayListExtra("projectInfoList");

        companyName = projectInfoList.get(0);
        projectName = projectInfoList.get(1);
        systemType = projectInfoList.get(2);
        systemStatus = projectInfoList.get(3);

        //Get questions texts from database
        getQuestionsText();

        //Init Spinner 1
        Spinner dropdown1 = findViewById(R.id.iFocusQuestion1);
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, questionTextList);
        dropdown1.setAdapter(adapter);

        //Init Spinner 2
        Spinner dropdown2 = findViewById(R.id.iFocusQuestion2);
        dropdown2.setAdapter(adapter);

        //Init Spinner 3
        Spinner dropdown3 = findViewById(R.id.iFocusQuestion3);
        dropdown3.setAdapter(adapter);

        //Set On Click Listeners for buttons
        Button btn = (Button) findViewById(R.id.btnCreateNewSurvey);
        Button btn2 = (Button) findViewById(R.id.btnCns2Back);
        Button btn3 = (Button) findViewById(R.id.btnCns2Menue);

        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            //Create new survey
            case R.id.btnCreateNewSurvey:
                createNewSurvey();
                break;


            //Go back to previous Activity
            case R.id.btnCns2Back:
                Intent intent1 = new Intent(this,CreateNewSurvey.class);
                startActivity(intent1);
                this.finish();
                break;

            //Go back to menue
            case R.id.btnCns2Menue:
                Intent intent2 = new Intent(this, Menue.class);
                startActivity(intent2);
                this.finish();
                break;


            default: break;
        }

    }

    private void createNewSurvey() {

        getProjectInformationRest();

        //Create new instance of Survey

        newSurvey = new Survey(surveyCode,companyName,projectName,systemType,systemStatus,participantAge,participantDepartment,participantPosition,focus1,focus2,focus3);

        //Save in database

        WriteToDB.saveSurveyInDatabase(newSurvey);

        //To-Do Create Survey Code & Display it
        surveyCode = newSurvey.getSurveyCode();

        //Show SurveyCode on the Screen
        TextView tvSurveyCode = (TextView) findViewById(R.id.tvSurveyCodeShow);
        tvSurveyCode.setText(surveyCode);
        Toast.makeText(this, "Umfrage erfolgreich erstellt", Toast.LENGTH_LONG).show();

        //Copy SurveyCode in Zwischenspeicher
        cbm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clipData1 = ClipData.newPlainText("surveyCode",surveyCode);
        cbm.setPrimaryClip(clipData1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), "Umfragecode wurde in Zwischenspeicher kopiert", Toast.LENGTH_LONG).show();
            }
        }, 1000);


    }

    private void getProjectInformationRest() {

        final CheckBox iCB1 = (CheckBox) findViewById(R.id.cbPersonalData1);
        final CheckBox iCB2 = (CheckBox) findViewById(R.id.cbPersonalData2);
        final CheckBox iCB3 = (CheckBox) findViewById(R.id.cbPersonalData3);

        final Spinner iSpinner1 = (Spinner) findViewById(R.id.iFocusQuestion1);
        final Spinner iSpinner2 = (Spinner) findViewById(R.id.iFocusQuestion2);
        final Spinner iSpinner3 = (Spinner) findViewById(R.id.iFocusQuestion3);

        participantAge = iCB1.isChecked();
        participantDepartment = iCB2.isChecked();
        participantPosition = iCB3.isChecked();
        focus1 = iSpinner1.getSelectedItem().toString();
        focus2 = iSpinner2.getSelectedItem().toString();
        focus3 = iSpinner3.getSelectedItem().toString();

    }

    public void getQuestionsText (){ //Get all question texts from database

        questionTextList.add("Keine Auswahl");
        DatabaseReference surveyDBRef = mRef.child("Question");

        surveyDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot sn : dataSnapshot.getChildren()) {
                    Question question = sn.getValue(Question.class);
                    if(question.getSystemCategory().equals(systemStatus)){
                        questionTextList.add(question.getQuestionText());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}
