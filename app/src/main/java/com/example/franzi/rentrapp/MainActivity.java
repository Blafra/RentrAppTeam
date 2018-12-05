package com.example.franzi.rentrapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStart;

    Survey sv;
    SpecificSurvey ss;
    Question[] questions = new Question[23];
    private DatabaseReference mRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //Konfiguration der Umfrage (wird abgefragt bei Umfrageerstellung)
        boolean[] config = {true, false};

        sv = new Survey("LALA", "SAP", "S4HANA", "ERP", "Neueinführung", questions, config);

        //Datenbank Fragen ziehen
        mRef = FirebaseDatabase.getInstance().getReference().child("Question");

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for(DataSnapshot sn : dataSnapshot.getChildren()){
                    Question question = sn.getValue(Question.class);

                    if(question.getSystemCategory().equals(sv.getSystemStatus()) || question.getSystemCategory().equals("Beides")){
                        questions[i] = question;
                        i++;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        ss = new SpecificSurvey(1, questions);
        sv.addSpecificSurvey(ss);

        btnStart = (Button) findViewById(R.id.btnStart);
        /* OnClickListener verwaltet das Klicken auf den Button */
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Individuell.class);
        intent.putExtra("Specific_Survey", ss);
        startActivity(intent);
        this.finish();
    }

    public static boolean filledOutCompletely(ArrayList<RadioButton> rbtnList) {
        //Prüfen ob alle Fragen beantwortet wurden

        int count = 0;
        boolean somethingChecked = false;

        for (RadioButton rbtn : rbtnList) {

            //Prüfen ob keines der Felder angeklickt wurde
            if (count > 4 && somethingChecked == false) {
                return false;
            }

            //Eines der Felder (von 5) wurde angeklickt --> Reste für nächste 5 Felder
            if (count > 4 && somethingChecked == true) {
                count = -1;
                somethingChecked = false;
            }

            //Prüfen ob Feld angeklickt wurde
            if (rbtn.isChecked()) {
                somethingChecked = true;
            }
            count++;

        }

        return true;
    }



    public static void saveQuestionResultValues (ArrayList<RadioButton> rbtnList, SpecificSurvey ss){


        //Liste durchlaufen und Antworten speichern

        int resultValue=5;
        int answerIdx = ss.getCurrentAnswerIdx();

        for(RadioButton rbtn : rbtnList){

            if(rbtn.isChecked()){
                ss.setAnswerArrayValues((answerIdx),resultValue);
                answerIdx++;
            }

            if(resultValue==1){
                resultValue=5;
            } else {
                resultValue--;
            }
        }

        //Speichern wo im Answer Array wir uns befinden
        ss.setCurrentAnswerIdx(answerIdx);


    }

  }







