package com.example.franzi.rentrapp;

import java.util.HashMap;
import java.util.Map;

public class Survey {

    private String surveyCode;
    private String companyName;
    private String projectName;
    private String systemType;
    private String systemStatus;
    private double resultTotal;
    public Map<String, Double> resultCategories;
    public Map<String,SpecificSurvey> specificSurveyList;

    //Constructor
    public Survey(String surveyCode, String companyName, String projectName, String systemType, String systemStatus, double resultTotal){
        this.surveyCode = surveyCode;
        this.companyName = companyName;
        this.projectName = projectName;
        this.systemType = systemType;
        this.systemStatus = systemStatus;
    }

    //Constructor
    public Survey(String surveyCode, String companyName, String projectName, String systemType, String systemStatus,double resultTotal,Map<String, Double> resultCategories,Map<String,SpecificSurvey> specificSurveyList){
        this.surveyCode = surveyCode;
        this.companyName = companyName;
        this.projectName = projectName;
        this.systemType = systemType;
        this.systemStatus = systemStatus;
        this.resultTotal = resultTotal;
        this.resultCategories = resultCategories;
        this.specificSurveyList = specificSurveyList;
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




    //Weiter Methoden

    public void calcResult(){
        int listLength = specificSurveyList.size();
        double [] results = new double[4];                         //Int Array für Ergebnisse (1) Gesammt (2) Individ (3) Orga (4) System


        for (Map.Entry e: specificSurveyList.entrySet()){

            //Gesamtergebnis und Kategorien aufsummiereun und dann durch Anzahl an specificSurveys teileun um Durchschnittswerte zu erhalten
            SpecificSurvey ss = (SpecificSurvey) e.getValue();
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


    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();

        result.put("surveyCode",surveyCode);
        result.put("companyName",companyName);
        result.put("projectName",projectName);
        result.put("systemType",systemType);
        result.put("systemStatus",systemStatus);
        result.put("resultTotal",resultTotal);
        result.put("resultCategories",resultCategories);
        result.put("specificSurveyList",specificSurveyList);

        return result;
    }

}
