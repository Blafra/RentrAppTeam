package com.example.franzi.rentrapp.Controller;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import com.example.franzi.rentrapp.Model.Question;
import com.example.franzi.rentrapp.Model.Survey;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RetrieveFromDB {


    //Get Survey from Database

    public static void getSurvey(final String surveyCode){

        //Hold collection of Surveys
        final List<Survey> surveys = new ArrayList<Survey>();

        //Database Ref
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getReference();
        DatabaseReference surveyDBRef = mRef.child("Survey");

        //Add Value Listener to get Data
        surveyDBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot sn : dataSnapshot.getChildren()){
                    Survey currentSurvey = sn.getValue(Survey.class);

                    if(currentSurvey.getSurveyCode().equals(surveyCode)){
                        surveys.add(currentSurvey);
                        break;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });



    }

    //Get QuestionList from Database

    public static List<Question> getQuestions(final Survey survey) {

        final List<Question> questionList = new ArrayList<Question>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getReference();
        DatabaseReference surveyDBRef = mRef.child("Question");

        surveyDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot sn : dataSnapshot.getChildren()) {
                    Question question = sn.getValue(Question.class);

                    if (question.getSystemCategory().equals(survey.getSystemStatus()) || question.getSystemCategory().equals("Beides") || question.getSystemCategory().equals("beides")) {
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

    //Get SpecificSurveyIdList from Database
    /*
    public ArrayList<String> getSpecificSurveyIdList(){

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("Survey").child(surveyCode).child("specificSurveyIdList");
        final ArrayList<String> specificSurveyIdList = new ArrayList<>();

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot sn : dataSnapshot.getChildren()){

                    specificSurveyIdList.add(sn.getValue().toString());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });

        return specificSurveyIdList;

    }*/
}
