package com.example.franzi.rentrapp.Controller;

import com.example.franzi.rentrapp.Model.Survey;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class WriteToDB {

    public static void saveSurveyInDatabase (Survey newSurvey){

        //Survey Objectinstanz in Datenbank abspeichern

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
