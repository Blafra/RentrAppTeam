package com.example.franzi.rentrapp.Model;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Survey {

    private String surveyCode;
    private String companyName;
    private String projectName;
    private String systemType;
    private String systemStatus;
    private double resultTotal;
    private ArrayList<String> specificSurveyIdList;
    public Map<String, Double> resultCategories;

    //Constructor

    //Constructor für Datenbankzugriff
    public Survey (){

    }

    public Survey(String surveyCode, String companyName, String projectName, String systemType, String systemStatus, double resultTotal){
        this.surveyCode = surveyCode;
        this.companyName = companyName;
        this.projectName = projectName;
        this.systemType = systemType;
        this.systemStatus = systemStatus;

        specificSurveyIdList = new ArrayList<String>();

        resultCategories = new HashMap<>();
        resultCategories.put("Individuell", null);
        resultCategories.put("System", null);
        resultCategories.put("Organistaion", null);

    }



    //Getter & Setter

    public String getSurveyCode() {
        return surveyCode;
    }

    public void setSurveyCode(String surveyCode) {
        this.surveyCode = surveyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getSystemStatus() {
        return systemStatus;
    }

    public void setSystemStatus(String systemStatus) {
        this.systemStatus = systemStatus;
    }

    public double getResultTotal() {
        return resultTotal;
    }

    public void setResultTotal(double resultTotal) {
        this.resultTotal = resultTotal;
    }

    public Map<String, Double> getResultCategories() {
        return resultCategories;
    }

    public void setResultCategories(Map<String, Double> resultCategories) {
        this.resultCategories = resultCategories;
    }

    public void setSpecificSurveyIdList(ArrayList<String> specificSurveyIdList) {
        this.specificSurveyIdList = specificSurveyIdList;
    }

    //Weiter Methoden

    public void calcResult(){

        //Alle Specific Surveys aus Datenbank abrufen
        ArrayList<SpecificSurvey> specificSurveyList = getSpecificSurveyList();

        int listLength = specificSurveyList.size();
        double [] results = new double[4];                         //Int Array für Ergebnisse (1) Gesammt (2) Individ (3) Orga (4) System


        for (SpecificSurvey ss : specificSurveyList){

            //Gesamtergebnis und Kategorien aufsummiereun und dann durch Anzahl an specificSurveys teileun um Durchschnittswerte zu erhalten
            double[] specificResults = ss.calcResult();

            results[0] += specificResults[0];
            results[1] += specificResults[1];
            results[2] += specificResults[2];
            results[3] += specificResults[3];

        }

        resultTotal = results[0]/listLength;
        resultCategories.put("Indviduell",results[1]/listLength);
        resultCategories.put("Organisation",results[1]/listLength);
        resultCategories.put("System",results[1]/listLength);
    }

    public boolean checkCode(String code){
        if(this.surveyCode==code){
            return true;
        }
        return false;
    }

    public ArrayList<SpecificSurvey> getSpecificSurveyList (){

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("SpecificSurvey");
        final ArrayList<SpecificSurvey> specificSurveyArrayList = new ArrayList<>();

        //Abfrage in Datenbank für alle SS Ids die Survey zugeordnet sind
        final ArrayList<String> specificSurveyIdList = getSpecificSurveyIdList();

        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot sn : dataSnapshot.getChildren()){

                    //Survey in Datenbank
                    SpecificSurvey currentSurvey = sn.getValue(SpecificSurvey.class);

                    //Gehe durch alle IDs in Liste des Surveys um enthaltene SS in SS Liste hinzuzufügen
                    for(String id : specificSurveyIdList){
                        if(id.equals(currentSurvey.getSpecificSurveyID())){
                            specificSurveyArrayList.add(currentSurvey);
                            break;
                        }
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });

        return specificSurveyArrayList;
    }

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

    }

    public void addSpecificSurveyToSurvey (SpecificSurvey ss){

        specificSurveyIdList.add(ss.getSpecificSurveyID());
        //Update Database
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child("Survey").child(surveyCode).child("specificSurveyIdList");
        mRef.setValue(ss.getSpecificSurveyID());

    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();

        result.put("surveyCode",surveyCode);
        result.put("companyName",companyName);
        result.put("projectName",projectName);
        result.put("systemType",systemType);
        result.put("systemStatus",systemStatus);
        result.put("resultTotal",resultTotal);

        return result;
    }

}
