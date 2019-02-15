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

    private HashMap<String, Result> Result;
    private String specificSurveyID;
    private String surveyID;
    private int employeeID;
    private List<Question> questionList;
    private boolean participantAge;
    private boolean participantDepartment;
    private boolean participantPosition;

    //Constructor

    //Constructor für Datenbankzugriff
    public SpecificSurvey() {

    }

    public SpecificSurvey(int employeeID, List<Question> questions, boolean participantAge, boolean participantDepartment, boolean participantPosition) {
        this.employeeID = employeeID;
        this.questionList = questions;
        this.participantAge = participantAge;
        this.participantDepartment = participantDepartment;
        this.participantPosition = participantPosition;
        this.specificSurveyID = generateSpecificSurveyId();

        //Iniziere Antwortarray Länge auf Anzahl der Fragen in Umfrage

        answerArray = new int[questionList.size()];
        currentQuestionIdx = 0;
        currentAnswerIdx = 0;

    }

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

    public String getSurveyID() {
        return surveyID;
    }
    //Getter & Setter

    public String getSpecificSurveyID() {
        return specificSurveyID;
    }

    public HashMap<String, Result> getResult() {
        return Result;
   }

   public void setResult(HashMap<String, Result> result) {
        this.Result = result;
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
        public void setSurveyID(String surveyID) {
            this.surveyID = surveyID;
        }

    public boolean isParticipantAge() {
        return participantAge;
    }

    public void setParticipantAge(boolean participantAge) {
        this.participantAge = participantAge;
    }

    public boolean isParticipantDepartment() {
        return participantDepartment;
    }

    public void setParticipantDepartment(boolean participantDepartment) {
        this.participantDepartment = participantDepartment;
    }

    public boolean isParticipantPosition() {
        return participantPosition;
    }

    public void setParticipantPosition(boolean participantPosition) {
        this.participantPosition = participantPosition;
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

    //Parcel für Übergabe der SS an nächste Activity

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





}


