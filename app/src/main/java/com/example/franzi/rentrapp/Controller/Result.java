package com.example.franzi.rentrapp.Controller;

import android.support.annotation.NonNull;

import com.example.franzi.rentrapp.Model.Question;
import com.example.franzi.rentrapp.Model.SpecificSurvey;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import com.example.franzi.rentrapp.Model.ResultDBModel;


public class Result {

    DatabaseReference mRef;

    //Ergebnisse in der Datenbank ablegen
    public void storeResult(List<Integer> results, String surveyCode, List<Integer> questionIDs) {
        int i = 0;
        int[] questions = new int[questionIDs.size()];
        int x = 0;

        for(int j : questionIDs){
            questions[x]= j;
            x++;
        }

        mRef = FirebaseDatabase.getInstance().getReference("SpecificSurvey/" + surveyCode);
        mRef.child("Result");


        for (int resultValue : results) {
            mRef = FirebaseDatabase.getInstance().getReference("SpecificSurvey/" + surveyCode + "Result");
            mRef.child("Result" + i);
            mRef = FirebaseDatabase.getInstance().getReference("SpecificSurvey/" + surveyCode + "Result/Result"+i);
            mRef.child("ResultValue").setValue(resultValue);
            mRef.child("QuestionID").setValue(questions[i]);
            i++;
        }


    }

    // Ergebnis pro Frage auswerten
    public double getResultPerQuestion(String surveyCode, final int questionID){

        final List<Double> results = new ArrayList<Double>();

        mRef = FirebaseDatabase.getInstance().getReference("SpecificSurvey/"+surveyCode+"Result");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sn : dataSnapshot.getChildren()){
                    ResultDBModel result = sn.getValue(ResultDBModel.class);
                    if(result.getQuestionID() == questionID){
                        results.add(result.getResultValue());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        int counter = 0;
        double sum = 0;
        double resultPerQuestion = 0;
        for(double res : results){
            sum += res;
            counter++;
        }

        resultPerQuestion = sum / counter;
        return resultPerQuestion;
    }

// Ergebnis pro Kategorie berechnen ############noch nicht fertig##########
    public double getResultPerCategory(String surveyCode, final String category ){
        final List<Double> results = new ArrayList<Double>();
        final List<Integer> questionIDs = new ArrayList<Integer>();


        mRef = FirebaseDatabase.getInstance().getReference("Question");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sn : dataSnapshot.getChildren()){
                    Question question = sn.getValue(Question.class);

                    if(question.getQuestionCategory().equals(category)){
                        questionIDs.add(question.getQuestionID());
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        mRef = FirebaseDatabase.getInstance().getReference("SpecificSurvey/"+surveyCode+"Result");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sn : dataSnapshot.getChildren()){

                    for (int questionID:questionIDs){

                    }
                    ResultDBModel result = sn.getValue(ResultDBModel.class);
                    results.add(result.getResultValue());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        int counter = 0;
        double sum = 0;
        double resultPerQuestion = 0;
        for(double res : results){
            sum += res;
            counter++;
        }

        resultPerQuestion = sum / counter;
        return resultPerQuestion;
    }
    // Gesamtergebnis berechnen ############noch nicht fertig##########
    public double getOverallResult(String surveyCode){
        final List<Double> results = new ArrayList<Double>();

        mRef = FirebaseDatabase.getInstance().getReference("SpecificSurvey/"+surveyCode+"Result");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sn : dataSnapshot.getChildren()){
                    ResultDBModel result = sn.getValue(ResultDBModel.class);
                    results.add(result.getResultValue());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        return 1;
    }

}
