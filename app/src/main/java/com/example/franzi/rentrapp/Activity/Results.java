package com.example.franzi.rentrapp.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.franzi.rentrapp.Model.Question;
import com.example.franzi.rentrapp.Model.Result;
import com.example.franzi.rentrapp.Model.SpecificSurvey;
import com.example.franzi.rentrapp.Model.Survey;
import com.example.franzi.rentrapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Results extends AppCompatActivity implements View.OnClickListener {


    DatabaseReference mRef;
    final List<Question> questions = new ArrayList<>();
    final List<SpecificSurvey> surveys = new ArrayList<>();
    final List<Result> surveyResults = new ArrayList<>();
    EditText averageQuestion1;
    EditText averageIndividual;
    Button button;
    String generalsurveyID;
    Survey generalSurvey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        generalsurveyID = getIntent().getExtras().getString("surveyCode");
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
        averageQuestion1 = (EditText) findViewById(R.id.etAverage);
        averageIndividual = (EditText)findViewById(R.id.etAvInd);
        getSurvey();
        getSpecificSurveys();

    }


    @Override
    public void onClick(View v) {
        //double average = getAverageOfCategory("Individuell");
       // Log.d("AVERAGE", "this is the aerage of question1: "+average);
        Log.d("TEST","#########surveys"+generalSurvey.getSurveyCode());
       // for(SpecificSurvey survey : surveys){
       //     Log.d("CHECK","###########"+survey.getResult().get("Result0").getResultValue());
      //  }

    }

    private double getAverageOfCategory(String questionCategory){
        double average;
        int sum =0;
        int i = 0;
        double counter = 0;
        HashMap<String, Result> resultMap = new HashMap<>();

           for(SpecificSurvey survey : surveys){
               resultMap = survey.getResult();

               for(Map.Entry<String, Result> entry : resultMap.entrySet()){
                   String key = "Result"+i;
                   if(entry.getKey().equals(key)){
                       if(entry.getValue().getQuestionCategory().equals(questionCategory)){
                           sum = sum + entry.getValue().getResultValue();
                           counter = counter+1;
                           i++;
                       };
                   }
               }
               i=0;

           }


        Log.d("CACULATION", "Sum"+sum);
        Log.d("AMOUNT","Counter"+counter);
        average = sum/counter;

        return average;
    }

    private void getSurvey(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getReference();
        DatabaseReference questionsDBRef = mRef.child("Survey");

        questionsDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot sn : dataSnapshot.getChildren()) {
                    Survey survey= sn.getValue(Survey.class);
                    if(survey.getSurveyCode().equals(generalsurveyID)){
                        generalSurvey = survey;
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void getSpecificSurveys(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getReference();
        final DatabaseReference surveyDBRef = mRef.child("SpecificSurvey");

        surveyDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot sn : dataSnapshot.getChildren()) {
                   SpecificSurvey survey = sn.getValue(SpecificSurvey.class);
                   Log.d("CHECK","survey:"+survey.getSpecificSurveyID());
                   Log.d("CHECK2", "value"+survey.getResult().get("Result0"));
                   if(survey.getSurveyID().equals(generalsurveyID)) {
                        surveys.add(survey);
                        }
                }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getQuestionData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getReference();
        DatabaseReference questionsDBRef = mRef.child("Question");

        questionsDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot sn : dataSnapshot.getChildren()) {
                    Question question = sn.getValue(Question.class);
                    questions.add(question);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
