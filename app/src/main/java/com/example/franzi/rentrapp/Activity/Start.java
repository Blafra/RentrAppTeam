package com.example.franzi.rentrapp.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.franzi.rentrapp.Activity.Execution.Individuell;
import com.example.franzi.rentrapp.R;
import com.example.franzi.rentrapp.Model.*;
import com.example.franzi.rentrapp.Controller.*;
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

    //Variablen für Datenbankabfragen
    final List<Survey> surveys = new ArrayList<Survey>();
    final List<Question> questionList = new ArrayList<Question>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mRef = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnStart = (Button) findViewById(R.id.btnStart);
        /* OnClickListener verwaltet das Klicken auf den Button */
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //Get survey code

        EditText iText = (EditText) findViewById(R.id.iStartSurveyCode);
        String surveyCode = iText.getText().toString();

        //Get survey instance form database and put information in "Survey" (sv) variable
        getSurvey(surveyCode);

        //Create Specific Survey Instance and put information in "Specifc Survey" (ss) variable
        if(!surveys.isEmpty()) {
           ss = createSpecificSurvey(sv);
        }else {
            Toast.makeText(this,"Code nicht gefunden",Toast.LENGTH_LONG).show();
            return;
        }
        //Go to next page and add survey Code

        Intent intent = new Intent(this, Individuell.class);
        intent.putExtra("Specific_Survey1", ss);
        startActivity(intent);
        this.finish();
    }



    public SpecificSurvey createSpecificSurvey(Survey sv){

        List<Question> questionList =  getQuestions(sv);

        Question [] questionArray = new Question[questionList.size()];
        questionArray = questionList.toArray(questionArray);

        ss = new SpecificSurvey(1, questionArray);



        ss = WriteToDB.saveSpecificSurveyInDatabase(ss);

        return ss;

    }

    //Get QuestionList from Database

    public List<Question> getQuestions(final Survey survey) {


        DatabaseReference surveyDBRef = mRef.child("Question");

        surveyDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot sn : dataSnapshot.getChildren()) {
                    Question question = sn.getValue(Question.class);

                    if (question.getSystemCategory().equals(survey.getSystemStatus())
                            || question.getSystemCategory().equals("Beides")
                            || question.getSystemCategory().equals("beides")) {
                        questionList.add(question);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return questionList;
    }

    public void getSurvey(final String surveyCode){

        DatabaseReference surveyDBRef = mRef.child("Survey");

        //Add Value Listener to get Data
        surveyDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Survey currentSurvey = new Survey();

                for(DataSnapshot sn : dataSnapshot.getChildren()){

                    currentSurvey = sn.getValue(Survey.class);

                    if(currentSurvey.getSurveyCode().equals(surveyCode)){
                        surveys.add(currentSurvey);
                        break;
                    }
                }

                Log.i(TAG, "Survey found:" + currentSurvey.getSurveyCode());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });



    }


}
