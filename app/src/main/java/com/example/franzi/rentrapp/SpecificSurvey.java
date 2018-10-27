package com.example.franzi.rentrapp;

public class SpecificSurvey{

    private int specificSurveyID;
    private int employeeID;
    private int[] answerArray;
    private double resultTotal;
    private double[] resultCategories = new double[3];
    private Question[] questionArray;
    private int currentQuestionIdx = 0;

    //Constructor

    public SpecificSurvey (int employeeID, Question[] questions){
        this.employeeID = employeeID;
        this.questionArray = questions;
        //Iniziere Antwortarray Länge auf Anzahl der Fragen in Umfrage

        answerArray = new int[questions.length];

    }

    //Getter & Setter

    public int[] getAnswerArray() {
        return answerArray;
    }

    public void setAnswerArray(int[] answerArray) {
        this.answerArray = answerArray;
    }

    public int getCurrentQuestionIdx() {
        return currentQuestionIdx;
    }

    public void setCurrentQuestionIdx(int currentQuestion) {
        if(currentQuestion>0) {
            this.currentQuestionIdx = currentQuestion;
        }
    }

    //Weitere Methoden

    public double getResultTotal(){
        calcResult();
        return resultTotal;
    }

    public double[] getResultCategories(){
        calcResult();
        return resultCategories;
    }

    public void calcResult(){
        double[] results = new double[4];                     //Int Array für Ergebnisse (1) Gesammt (2) Individ (3) Orga (4) System
        int[] counter = new int[4];                           //Counter wird für Durchschnittsberechnung mitgeführt

        for(int i=0;i<currentQuestionIdx;i++) {

            //Gesamtergebniss aufsummieren
            results[0] += answerArray[i];
            counter[0]++;

            //Kategorien
            if (questionArray[i].isCategoryIndiv()) {
                results[1] += (double) answerArray[i];
            }
            counter[1]++;
            if (questionArray[i].isCategoryOrga()) {
                results[2] += (double) answerArray[i];
            }
            counter[2]++;
            if (questionArray[i].isCategorySys()) {
                results[3] += (double) answerArray[i];
            }
            counter[3]++;
        }

        //Mittelwert berechnen
        resultTotal = results[0]/counter[0];
        resultCategories[0] = results[1]/counter[1];
        resultCategories[1] = results[2]/counter[2];
        resultCategories[2] = results[3]/counter[3];
    }

    public void tranfResult(){

    }

}
