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

    public static SpecificSurvey saveSpecificSurveyInDatabase(SpecificSurvey ss) {

        //Survey Objectinstanz in Datenbank abspeichern

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getReference();

        //To-Do SurveyCode

        String key = mRef.child("SpecificSurvey").push().getKey();
        ss.setSpecificSurveyID(key);

        //Transform Input into HashMap of Survey and add to database
        String test = "Hallo";
        Map<String, Object> specificSurveyMap = ss.toMap(test);

        mRef.child("SpecificSurvey").child(key).setValue(specificSurveyMap);

        //Retrun brauch man hier glaub nicht da ss Referenz auf gelichen Speicher?!
        return ss;
    }
}
