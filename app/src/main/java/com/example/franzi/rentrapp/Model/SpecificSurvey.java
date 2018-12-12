package com.example.franzi.rentrapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpecificSurvey implements Parcelable {

    private String specificSurveyID;
    private int employeeID;
    private int[] answerArray;
    private int currentAnswerIdx;
    private List<Question> questionList;
    private int currentQuestionIdx;
    private int surveyID;

    //Constructor

    //Constructor für Datenbankzugriff
    public SpecificSurvey() {

    }

    public SpecificSurvey(int employeeID, List<Question> questions) {
        this.employeeID = employeeID;
        this.questionList = questions;
        //Iniziere Antwortarray Länge auf Anzahl der Fragen in Umfrage

        answerArray = new int[questionList.size()];
        currentQuestionIdx = 0;
        currentAnswerIdx = 0;

        this.specificSurveyID = generateSpecificSurveyId();

        //To-DO Save in Database
    }


    //Parcelabel




    //Getter & Setter

    protected SpecificSurvey(Parcel in) {
        specificSurveyID = in.readString();
        employeeID = in.readInt();
        answerArray = in.createIntArray();
        currentAnswerIdx = in.readInt();
        questionList = in.createTypedArrayList(Question.CREATOR);
        currentQuestionIdx = in.readInt();
        surveyID = in.readInt();
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

    public int[] getAnswerArray() {
        return answerArray;
    }

    public void setAnswerArray(int[] answerList) {
        this.answerArray = answerList;
    }

    public int getCurrentAnswerIdx() {
        return currentAnswerIdx;
    }

    public void setCurrentAnswerIdx(int currentAnswerIdx) {
        this.currentAnswerIdx = currentAnswerIdx;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question>  questionList) {
        this.questionList = questionList;
    }

    public int getCurrentQuestionIdx() {
        return currentQuestionIdx;
    }

    public void setCurrentQuestionIdx(int currentQuestionIdx) {
        this.currentQuestionIdx = currentQuestionIdx;
    }

    public int getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(int surveyID) {
        this.surveyID = surveyID;
    }


    //Weitere Methoden

    public void setAnswerArrayValues(int answerIdx, int resultValue) {
        this.answerArray[answerIdx] = resultValue;
    }

    public void calcResult() {
     /*   double[] results = new double[4];                     //Int Array für Ergebnisse (1) Gesammt (2) Individ (3) Orga (4) System
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
        */

    }

    private String generateSpecificSurveyId() {

        //Verbindung zur Datenbank

        DatabaseReference surveyDatabase = FirebaseDatabase.getInstance().getReference();

        //Abfrage einer noch nicht vergebenen ID

        String key = surveyDatabase.child("SpecificSurvey").push().getKey();

        return key;

    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("specificSurveyID", specificSurveyID);
        result.put("employeeID", employeeID);
        result.put("currentAnswerIdx", currentAnswerIdx);
        result.put("currentQuestionIdx", currentQuestionIdx);
        result.put("surveyID", surveyID);

        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(specificSurveyID);
        dest.writeInt(employeeID);
        dest.writeIntArray(answerArray);
        dest.writeInt(currentAnswerIdx);
        dest.writeTypedList(questionList);
        dest.writeInt(currentQuestionIdx);
        dest.writeInt(surveyID);
    }


    //Parcel Methoden



}


