package com.example.franzi.rentrapp.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.franzi.rentrapp.Activity.Execution.Individuell;
import com.example.franzi.rentrapp.R;
import com.example.franzi.rentrapp.Model.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Start extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference mRef;
    private Survey sv;
    private SpecificSurvey ss;
    private ArrayList<Question> questionList = new ArrayList<Question>();
    private Button btnStart;

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
        if(sv!=null) {
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

    public void getSurvey(final String surveyCode){

        mRef = FirebaseDatabase.getInstance().getReference().child("Survey");

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot sn : dataSnapshot.getChildren()){

                    Survey currentSurvey = sn.getValue(Survey.class);

                    if(currentSurvey.getSurveyCode().equals(surveyCode)){
                        sv =currentSurvey;
                        break;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });

    }

    public void getQuestions(final Survey survey){

        mRef = FirebaseDatabase.getInstance().getReference().child("Question");

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot sn : dataSnapshot.getChildren()){
                    Question question = sn.getValue(Question.class);

                    if(question.getSystemCategory().equals(survey.getSystemStatus()) || question.getSystemCategory().equals("Beides") || question.getSystemCategory().equals("beides")){
                        questionList.add(question);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       /* mRef = FirebaseDatabase.getInstance().getReference().child("Question");

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot sn : dataSnapshot.getChildren()){
                    Question question = sn.getValue(Question.class);

                    if(question.getSystemCategory().equals(survey.getSystemStatus()) || question.getSystemCategory().equals("Beides") || question.getSystemCategory().equals("beides")){
                        questionList.add(question);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }

    public SpecificSurvey createSpecificSurvey(Survey sv){

        DatabaseReference specificSurveyDatabase = FirebaseDatabase.getInstance().getReference();

        getQuestions(sv);
        Question [] questionArray = new Question[questionList.size()];
        questionArray = questionList.toArray(questionArray);
        ss = new SpecificSurvey(1, questionArray);

        sv.addSpecificSurveyToSurvey(ss);

        return ss;

    }


}
