package com.example.franzi.rentrapp.Model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

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
    DatabaseReference mRef;

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

    }

    public void saveSpecificSurvey(){

        this.specificSurveyID = generateSpecificSurveyId();
        Log.d("CHECK",specificSurveyID);
        //To-DO Save in Database
        mRef = FirebaseDatabase.getInstance().getReference("SpecificSurvey");
        mRef.child(specificSurveyID).setValue(toMap());
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
            result.put("surveyID", surveyID);
            result.put("participantAge", participantAge);
            result.put("participantDepartment", participantDepartment);
            result.put("participantPosition", participantPosition);

            return result;
        }



    //Getter Setter
    public HashMap<String, com.example.franzi.rentrapp.Model.Result> getResult() {
        return Result;
    }

    public void setResult(HashMap<String, com.example.franzi.rentrapp.Model.Result> result) {
        Result = result;
    }

    public String getSpecificSurveyID() {
        return specificSurveyID;
    }

    public void setSpecificSurveyID(String specificSurveyID) {
        this.specificSurveyID = specificSurveyID;
    }

    public String getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(String surveyID) {
        this.surveyID = surveyID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
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

    //Parcable
    protected SpecificSurvey(Parcel in) {
        specificSurveyID = in.readString();
        surveyID = in.readString();
        employeeID = in.readInt();
        questionList = in.createTypedArrayList(Question.CREATOR);
        participantAge = in.readByte() != 0;
        participantDepartment = in.readByte() != 0;
        participantPosition = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(specificSurveyID);
        dest.writeString(surveyID);
        dest.writeInt(employeeID);
        dest.writeTypedList(questionList);
        dest.writeByte((byte) (participantAge ? 1 : 0));
        dest.writeByte((byte) (participantDepartment ? 1 : 0));
        dest.writeByte((byte) (participantPosition ? 1 : 0));
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