package com.example.franzi.rentrapp.Model;

public class ResultDBModel {

    double resultValue;
    int QuestionID;

    public void setResultValue(int resultValue){
        this.resultValue = resultValue;
    }

    public double getResultValue(){
        return this.resultValue;
    }

    public void setQuestionID(int QuestionID){

        this.QuestionID = QuestionID;
    }

    public int getQuestionID(){
        return this.QuestionID;
    }

}
