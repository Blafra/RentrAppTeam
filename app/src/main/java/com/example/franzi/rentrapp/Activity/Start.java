package com.example.franzi.rentrapp.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.franzi.rentrapp.Activity.Execution.Individuell;
import com.example.franzi.rentrapp.Activity.Execution.ParticipantInput;
import com.example.franzi.rentrapp.R;
import com.example.franzi.rentrapp.Model.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class Start extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "";
    private Survey sv;
    private SpecificSurvey ss;
    private Button btnStart;
    private Button btnBack;
   // final String surveyCode;


    //Variablen für Datenbankabfragen
    final List<Survey> allSurveyList = new ArrayList<Survey>();
    final List<Question> allQuestionList = new ArrayList<Question>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mRef = database.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);

        getSurveyData();
        getQuestionData();


        btnStart = (Button) findViewById(R.id.btn_Start);
        /* OnClickListener verwaltet das Klicken auf den Button */
        btnStart.setOnClickListener(this);

        btnBack = (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_Start:

             //Get survey code
             EditText iText = (EditText) findViewById(R.id.iStartSurveyCode);
              final String surveyCode = iText.getText().toString();
                Survey sv = new Survey();

             //Look for the survey in the Survey List
                for (Survey s : allSurveyList) {
                    if (s.getSurveyCode().equals(surveyCode)) {
                        sv = s;
                        break;
                    }
                }

                //Create Specific Survey Instance and put information in "Specifc Survey" (ss) variable

               if (sv != null) {
                   ss = createSpecificSurvey(sv);
                } else {
                   //Toast.makeText(this,R.string.codenotfound_createnewsurvey, Toast.LENGTH_LONG).show();
                   return;
                }


                //Go to next page and add survey Code

                Intent intent = new Intent(this, ParticipantInput.class);
                intent.putExtra("Specific_Survey1", ss);
                startActivity(intent);
                this.finish();
                break;

            case R.id.btn_back:

                Intent intent2 = new Intent(this, Menue.class);
                startActivity(intent2);
                this.finish();
                break;

            default: break;
        }
    }

    public SpecificSurvey createSpecificSurvey (Survey sv){

        List<Question> questionList =  getQuestions(sv);

        ss = new SpecificSurvey(1, questionList, sv.isParticipantAge(), sv.isParticipantDepartment(), sv.isParticipantPosition());

        return ss;
    }

    public List<Question> getQuestions(Survey sv){

        List<Question> surveyQuestionList = new ArrayList<>();

        for(Question q : allQuestionList){
            String projectSystemCategory = sv.getSystemStatus();
            String questionSystemCategory= q.getSystemCategory();

            if(projectSystemCategory.equalsIgnoreCase(questionSystemCategory)){
                surveyQuestionList.add(q);
            } else if (questionSystemCategory.equalsIgnoreCase("Beides")){
                surveyQuestionList.add(q);
            }
        }

        return surveyQuestionList;
    }

    public void getSurveyData(){
        DatabaseReference surveyDBRef = mRef.child("Survey");
        surveyDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot sn : dataSnapshot.getChildren()){
                    Survey currentSurvey = sn.getValue(Survey.class);
                    allSurveyList.add(currentSurvey);
                }

                Log.i(TAG, "Survey found:");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void getQuestionData(){

    DatabaseReference surveyDBRef = mRef.child("Question");

        surveyDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot sn : dataSnapshot.getChildren()) {
                    Question question = sn.getValue(Question.class);
                    allQuestionList.add(question);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
