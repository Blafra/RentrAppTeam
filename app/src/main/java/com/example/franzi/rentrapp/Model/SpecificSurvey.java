package com.example.franzi.rentrapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SpecificSurvey implements Parcelable {

    private String specificSurveyID;
    private int employeeID;
    private int[] answerArray;
    private int currentAnswerIdx;
    private Question[] questionArray;
    private int currentQuestionIdx;
    private int surveyID;

    //Constructor

    //Constructor für Datenbankzugriff
    public SpecificSurvey(){

    }

    public SpecificSurvey (int employeeID, Question[] questions){
        this.employeeID = employeeID;
        this.questionArray = questions;
        //Iniziere Antwortarray Länge auf Anzahl der Fragen in Umfrage

        answerArray = new int[questions.length];
        currentQuestionIdx = 0;
        currentAnswerIdx = 0;

        this.specificSurveyID = generateSpecificSurveyId();

        //To-DO Save in Database
    }



    //Getter & Setter

    protected SpecificSurvey(Parcel in) {
        specificSurveyID = in.readString();
        employeeID = in.readInt();
        answerArray = in.createIntArray();
        currentAnswerIdx = in.readInt();
        questionArray = in.createTypedArray(Question.CREATOR);
        currentQuestionIdx = in.readInt();
    }


    public int[] getAnswerArray() {
        return answerArray;
    }

    public void setAnswerArray(int[] answerArray) {
        this.answerArray = answerArray;
    }

    public void setAnswerArrayValues(int index, int value){
        answerArray[index] = value;
    }

    public int getCurrentQuestionIdx() {
        return currentQuestionIdx;
    }

    public void setCurrentQuestionIdx(int currentQuestion) {
        if(currentQuestion>0) {
            this.currentQuestionIdx = currentQuestion;
        }
    }

    public Question[] getQuestionArray() {
        return questionArray;
    }

    public void setQuestionArray(Question[] questionArray) {
        this.questionArray = questionArray;
    }


    public int getCurrentAnswerIdx() {
        return currentAnswerIdx;
    }

    public void setCurrentAnswerIdx(int currentAnswerIdx) {
        this.currentAnswerIdx = currentAnswerIdx;
    }

    public String getSpecificSurveyID() {
        return specificSurveyID;
    }

    public void setSpecificSurveyID(String specificSurveyID) {
        this.specificSurveyID = specificSurveyID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(int surveyID) {
        this.surveyID = surveyID;
    }

    //Weitere Methoden



    public double[] calcResult(){
        double[] results = new double[4];                     //Int Array für Ergebnisse (1) Gesammt (2) Individ (3) Orga (4) System
        int[] counter = new int[4];                           //Counter wird für Durchschnittsberechnung mitgeführt

        for(int i=0;i<currentQuestionIdx;i++) {


            //Gesamtergebniss aufsummieren
            results[0] += answerArray[i];
            counter[0]++;

            //Kategorien
            if (questionArray[i].getQuestionCategory().equals("Individuell")) {
                results[1] += (double) answerArray[i];
                counter[1]++;
            }

            if (questionArray[i].getQuestionCategory().equals("Organisatorisch")) {
                results[2] += (double) answerArray[i];
                counter[2]++;
            }
            if (questionArray[i].getQuestionCategory().equals("System")) {
                results[3] += (double) answerArray[i];
                counter[3]++;
            }

        }

        //Mittelwert berechnen
        results[0] = results[0]/counter[0];
        results[1] = results[1]/counter[1];
        results[2] = results[2]/counter[2];
        results[3] = results[3]/counter[3];

        return results;
    }

    private String generateSpecificSurveyId() {

        //Verbindung zur Datenbank

        DatabaseReference surveyDatabase = FirebaseDatabase.getInstance().getReference();

        //Abfrage einer noch nicht vergebenen ID

        String key = surveyDatabase.child("SpecificSurvey").push().getKey();

        return key;

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(specificSurveyID);
        dest.writeInt(employeeID);
        dest.writeIntArray(answerArray);
        dest.writeInt(currentAnswerIdx);
        dest.writeTypedArray(questionArray, flags);
        dest.writeInt(currentQuestionIdx);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SpecificSurvey> CREATOR = new Creator<SpecificSurvey>() {
        @Override
        public SpecificSurvey createFromParcel(Parcel in) {
            return new SpecificSurvey(in);
        }

        @Override
        public SpecificSurvey[] newArray(int size) {
            return new SpecificSurvey[size];
        }
    };


    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();

        result.put("specificSurveyID",specificSurveyID);
        result.put("employeeID",employeeID);
        result.put("answerArray",answerArray);
        result.put("currentAnswerIdx",currentAnswerIdx);
        result.put("questionArray",questionArray);
        result.put("currentQuestionIdx",currentQuestionIdx);
        result.put("surveyID",surveyID);

        return result;
    }

}
