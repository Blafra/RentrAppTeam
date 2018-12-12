package com.example.franzi.rentrapp.Model;

public class ResultDBModel {

    double resultValue;
    int QuestionID;

    public void setResultValue(int resultValue){
        double dummy = 1;

        this.resultValue = resultValue * dummy;
    }

    public double getResultValue(){
        return this.resultValue;
    }

    public void setQuestionID(int questionID){

        this.QuestionID = questionID;
    }

    public int getQuestionID(){
        return this.QuestionID;
    }

}
