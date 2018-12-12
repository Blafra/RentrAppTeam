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


    //Constructor

    //Constructor für Datenbankzugriff
    public Survey (){

    }

    public Survey(String surveyCode, String companyName, String projectName, String systemType, String systemStatus){
        this.surveyCode = surveyCode;
        this.companyName = companyName;
        this.projectName = projectName;
        this.systemType = systemType;
        this.systemStatus = systemStatus;
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



    //Weiter Methoden

    public void calcResult(){

      //TO-DO

    }

    public boolean checkCode(String code){

        if(this.surveyCode==code){
            return true;
        }
        return false;
    }


    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();

        result.put("surveyCode",surveyCode);
        result.put("companyName",companyName);
        result.put("projectName",projectName);
        result.put("systemType",systemType);
        result.put("systemStatus",systemStatus);

        return result;
    }

}
