package com.example.franzi.rentrapp;

import android.os.Parcel;
import android.os.Parcelable;

public class SpecificSurvey implements Parcelable {

    private int specificSurveyID;
    private int employeeID;
    private int[] answerArray;
    private int currentAnswerIdx;
    private Question[] questionArray;
    private int currentQuestionIdx;

    //Constructor

    public SpecificSurvey (int employeeID, Question[] questions){
        this.employeeID = employeeID;
        this.questionArray = questions;
        //Iniziere Antwortarray Länge auf Anzahl der Fragen in Umfrage

        answerArray = new int[questions.length];
        currentQuestionIdx = 0;
        currentAnswerIdx = 0;
    }

    //Getter & Setter

    protected SpecificSurvey(Parcel in) {
        specificSurveyID = in.readInt();
        employeeID = in.readInt();
        answerArray = in.createIntArray();
        currentAnswerIdx = in.readInt();
        questionArray = in.createTypedArray(Question.CREATOR);
        currentQuestionIdx = in.readInt();
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

    public void tranfResult(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(specificSurveyID);
        dest.writeInt(employeeID);
        dest.writeIntArray(answerArray);
        dest.writeInt(currentAnswerIdx);
        dest.writeTypedArray(questionArray, flags);
        dest.writeInt(currentQuestionIdx);
    }
}
