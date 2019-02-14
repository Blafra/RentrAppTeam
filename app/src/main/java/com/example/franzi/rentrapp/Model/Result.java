package com.example.franzi.rentrapp.Model;

public class Result {

    private int resultValue;
    private int QuestionID;
    private String questionCategory;

    public void setQuestionCategory(String questionCategory) {
        this.questionCategory = questionCategory;
    }

    public String getQuestionCategory() {
        return questionCategory;
    }

    public void setResultValue(int resultValue){
        this.resultValue = resultValue;
    }

    public int getResultValue(){
        return this.resultValue;
    }

    public void setQuestionID(int QuestionID){
        this.QuestionID = QuestionID;
    }

    public int getQuestionID(){
        return this.QuestionID;
    }
}
