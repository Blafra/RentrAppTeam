package com.example.franzi.rentrapp;

import java.util.ArrayList;

public class Survey {

    private int surveyID;
    private String surveyCode;
    private String companyName;
    private String projectName;
    private String systemType;
    private String systemStatus;
    private Question[] questions;
    private double resultTotal;
    private double[] resultCategories;
    private boolean[] configuration;
    private ArrayList<SpecificSurvey> specificSurveyList = new ArrayList<>();

    //Constructor
    public Survey(String surveyCode, String companyName, String projectName, String systemType, String systemStatus, Question[] questions, boolean[] config){
        this.surveyCode = surveyCode;
        this.companyName = companyName;
        this.projectName = projectName;
        this.systemType = systemType;
        this.systemStatus = systemStatus;
        this.questions = questions;
        this.configuration = config;
    }

    //Getter & Setter
    public int getSurveyID() {
        return surveyID;
    }

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

    public Question[] getQuestions() {
        return questions;
    }

    public void setQuestions(Question[] questions) {
        this.questions = questions;
    }

    public double getResultTotal() {
        return resultTotal;
    }

    public void setResultTotal(double resultTotal) {
        this.resultTotal = resultTotal;
    }

    public double[] getResultCategories() {
        return resultCategories;
    }

    public void setResultCategories(double[] resultCategories) {
        this.resultCategories = resultCategories;
    }

    public boolean[] getConfiguration() {
        return configuration;
    }

    public void setConfiguration(boolean[] configuration) {
        this.configuration = configuration;
    }


    //Weiter Methoden

    public void calcResult(){
        int listLength = specificSurveyList.size();
        double [] results = new double[4];                         //Int Array für Ergebnisse (1) Gesammt (2) Individ (3) Orga (4) System


        for (SpecificSurvey s : specificSurveyList){

            //Gesamtergebnis und Kategorien aufsummiereun und dann durch Anzahl an specificSurveys teileun um Durchschnittswerte zu erhalten

            results[0] += s.getResultTotal();
            results[1] += s.getResultCategories()[0];
            results[2] += s.getResultCategories()[1];
            results[3] += s.getResultCategories()[2];

        }

        resultTotal = results[0]/listLength;
        resultCategories[0] = results[1]/listLength;
        resultCategories[2] = results[2]/listLength;
        resultCategories[3] = results[3]/listLength;
    }


    public boolean checkCode(String code){
        if(this.surveyCode==code){
            return true;
        }
        return false;
    }


}
