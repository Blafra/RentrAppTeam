package com.example.franzi.rentrapp.Model;

import java.util.List;

public class SpecificSurveyDBModel {

    private List<ResultDBModel> Result;
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

    public List<ResultDBModel> getResult() {
        return Result;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setResult(List<ResultDBModel> result) {
        Result = result;
    }

    public void setSpecificSurveyID(String specificSurveyID) {
        this.specificSurveyID = specificSurveyID;
    }

    public void setSurveyID(String surveyID) {
        this.surveyID = surveyID;
    }
}
