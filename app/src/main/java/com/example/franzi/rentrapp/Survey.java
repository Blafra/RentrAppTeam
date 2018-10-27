package com.example.franzi.rentrapp;

public class Survey {

    private int surveyID;
    private String surveyPassword;
    private String companyName;
    private String projectName;
    private String systemType;
    private String systemStatus;
    private Question[] questions;
    private double resultTotal;
    private double[] resultCategories;
    private boolean configuration[];


    //Constructor
    public Survey(){

    }

    //Getter & Setter
    public int getSurveyID() {
        return surveyID;
    }

    public String getSurveyPassword() {
        return surveyPassword;
    }

    public void setSurveyPassword(String surveyPassword) {
        this.surveyPassword = surveyPassword;
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

    public void generateID(){

    }

    public void generateSurvey(){

    }

    public boolean checkPasssword(int id, String passwort){
        return true;
    }

    public void checkSurvey(int id){

    }

    public void getSurvey(int id){

    }
}
