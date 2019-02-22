package com.example.franzi.rentrapp.Model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Result  {

    DatabaseReference mRef;
    private int QuestionID;
    private int resultValue;
    private String questionCategory;


    //Constructor
    public Result(){

    }

    //Getter Setter

    public int getQuestionID() {
        return QuestionID;
    }
    public void setQuestionID(int questionID){
        this.QuestionID = questionID;
    }
    public int getResultValue() {
        return resultValue;
    }
    public void setResultValue(int resultValue){
        this.resultValue = resultValue;
    }

    public String getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(String questionCategory) {
        this.questionCategory = questionCategory;
    }

    //store in Database

    public static void storeResults(String specificSurveyID, List<Result> results){

       int i = 0;
       for(Result result:results){
           result.storeValues(specificSurveyID,i);
           i++;
       }
    }


    public void storeValues(String specificSurveyID, int i){
        mRef = FirebaseDatabase.getInstance().getReference("SpecificSurvey/"+specificSurveyID+"/Result");
        mRef.child("Result"+i).setValue(toMap());
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();

        result.put("QuestionID",this.QuestionID);
        result.put("resultValue",this.resultValue);
        result.put("questionCategory",this.questionCategory);

        return result;
    }




}
