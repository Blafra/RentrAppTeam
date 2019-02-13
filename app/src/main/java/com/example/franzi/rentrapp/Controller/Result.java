package com.example.franzi.rentrapp.Controller;

import android.support.annotation.NonNull;

import com.example.franzi.rentrapp.Model.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
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

   /* //get questions and result Lists for Calculation
    public List<SpecificSurvey> getSurveyList(){

        final String surveyID = "-LU4QQ04QLJKLfhO-LAv";


        mRef = FirebaseDatabase.getInstance().getReference("SpecificSurvey");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sn : dataSnapshot.getChildren()){
                    SpecificSurvey survey = sn.getValue(SpecificSurvey.class);
                        if(survey.getSurveyID().equals(surveyID)){
                            surveys.add(survey);
                        }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        return surveys;

    }*/
   /* public double calculateResult(SpecificSurvey ss){
        List<Result> answersInd = ss.getAnswersIndividuell();
        int questionAmount = answersInd.size();
        int sum = 0;
       double averageIndividuell = 0;

       for(int i : answersInd){
           sum = sum +i;
       }
       averageIndividuell = (sum/questionAmount)*1.0;
       return averageIndividuell;
    }*/

    // Ergebnis pro Frage auswerten
  /*  public double getResultPerQuestion(String surveyCode, final int questionID){

        final List<Double> results = new ArrayList<Double>();

        mRef = FirebaseDatabase.getInstance().getReference("SpecificSurveyRe/"+surveyCode+"Result");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sn : dataSnapshot.getChildren()){
                    ResultDBModel result = sn.getValue(ResultDBModel.class);
                    if(result.getQuestionID() == questionID){
                        results.add(result.getResultValue());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        int counter = 0;
        double sum = 0;
        double resultPerQuestion = 0;
        for(double res : results){
            sum += res;
            counter++;
        }

        resultPerQuestion = sum / counter;
        return resultPerQuestion;
    }*/

// Ergebnis pro Kategorie berechnen ############noch nicht fertig##########
  /*  public double getResultPerCategory(String surveyCode, final String category ){
        final List<Double> results = new ArrayList<Double>();
        final List<Integer> questionIDs = new ArrayList<Integer>();


        mRef = FirebaseDatabase.getInstance().getReference("Question");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sn : dataSnapshot.getChildren()){
                    Question question = sn.getValue(Question.class);

                    if(question.getQuestionCategory().equals(category)){
                        questionIDs.add(question.getQuestionID());
                    }

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        mRef = FirebaseDatabase.getInstance().getReference("SpecificSurvey/"+surveyCode+"Result");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sn : dataSnapshot.getChildren()){

                    for (int questionID:questionIDs){

                    }
                    ResultDBModel result = sn.getValue(ResultDBModel.class);
                    results.add(result.getResultValue());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        int counter = 0;
        double sum = 0;
        double resultPerQuestion = 0;
        for(double res : results){
            sum += res;
            counter++;
        }

        resultPerQuestion = sum / counter;
        return resultPerQuestion;
    }
    // Gesamtergebnis berechnen ############noch nicht fertig##########
    public double getOverallResult(String surveyCode){
        final List<Double> results = new ArrayList<Double>();

        mRef = FirebaseDatabase.getInstance().getReference("SpecificSurvey/"+surveyCode+"Result");
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot sn : dataSnapshot.getChildren()){
                    ResultDBModel result = sn.getValue(ResultDBModel.class);
                    results.add(result.getResultValue());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        return 1;
    }*/

}
