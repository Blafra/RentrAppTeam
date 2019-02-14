package com.example.franzi.rentrapp.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpecificSurvey {

    private HashMap<String, Result> Result;
    private int employeeID;
    private String specificSurveyID;
    private String surveyID;


    public int getEmployeeID() {
        return employeeID;
    }

    public String getSurveyID() {
        return surveyID;
    }

    public String getSpecificSurveyID() {
        return specificSurveyID;
    }

    public HashMap<String, Result> getResult() {
        return Result;
   }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

   public void setResult(HashMap<String, Result> result) {
        this.Result = result;
    }

    public void setSpecificSurveyID(String specificSurveyID) {
        this.specificSurveyID = specificSurveyID;
    }

    public void setSurveyID(String surveyID) {
        this.surveyID = surveyID;
    }
}
