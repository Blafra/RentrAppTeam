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



    private boolean settingParticipantAge;
    private boolean settingParticipantDepartment;
    private boolean settingParticipantPosition;

    private int participantAgeGroup;                //0: Agegroup was not asked, 1: 16-25, 2: 26-35, 3: 36-50, 4: 50+
    private String participantDepartment;


    private boolean isManager;
    private int numbEmployees;

    DatabaseReference mRef;

    //Constructor

    //Constructor für Datenbankzugriff
    public SpecificSurvey() {

    }

    public SpecificSurvey(String surveyID, int employeeID, List<Question> questions, boolean participantAge, boolean participantDepartment, boolean participantPosition) {

        this.employeeID = employeeID;
        this.questionList = questions;
        this.settingParticipantAge = participantAge;
        this.settingParticipantDepartment = participantDepartment;
        this.settingParticipantPosition = participantPosition;
        this.specificSurveyID = generateSpecificSurveyId();
        this.surveyID = surveyID;

    }

    protected SpecificSurvey(Parcel in) {
        specificSurveyID = in.readString();
        surveyID = in.readString();
        employeeID = in.readInt();
        questionList = in.createTypedArrayList(Question.CREATOR);
        settingParticipantAge = in.readByte() != 0;
        settingParticipantDepartment = in.readByte() != 0;
        settingParticipantPosition = in.readByte() != 0;
        participantAgeGroup = in.readInt();
        participantDepartment = in.readString();
        isManager = in.readByte() != 0;
        numbEmployees = in.readInt();
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
            result.put("participantAge", participantAgeGroup);
            result.put("participantDepartment", participantDepartment);
            result.put("isManager", isManager);
            result.put("numbEmployees", numbEmployees);

            return result;
        }



    //Getter Setter


    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public boolean isSettingParticipantAge() {
        return settingParticipantAge;
    }

    public void setSettingParticipantAge(boolean settingParticipantAge) {
        this.settingParticipantAge = settingParticipantAge;
    }

    public boolean isSettingParticipantDepartment() {
        return settingParticipantDepartment;
    }

    public void setSettingParticipantDepartment(boolean settingParticipantDepartment) {
        this.settingParticipantDepartment = settingParticipantDepartment;
    }

    public boolean isSettingParticipantPosition() {
        return settingParticipantPosition;
    }

    public void setSettingParticipantPosition(boolean settingParticipantPosition) {
        this.settingParticipantPosition = settingParticipantPosition;
    }

    public int getParticipantAgeGroup() {
        return participantAgeGroup;
    }

    public void setParticipantAgeGroup(int participantAgeGroup) {
        this.participantAgeGroup = participantAgeGroup;
    }

    public String getParticipantDepartment() {
        return participantDepartment;
    }

    public void setParticipantDepartment(String participantDepartment) {
        this.participantDepartment = participantDepartment;
    }



    public int getNumbEmployees() {
        return numbEmployees;
    }

    public void setNumbEmployees(int numbEmployees) {
        this.numbEmployees = numbEmployees;
    }

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


    //Parcable
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(specificSurveyID);
        dest.writeString(surveyID);
        dest.writeInt(employeeID);
        dest.writeTypedList(questionList);
        dest.writeByte((byte) (settingParticipantAge ? 1 : 0));
        dest.writeByte((byte) (settingParticipantDepartment ? 1 : 0));
        dest.writeByte((byte) (settingParticipantPosition ? 1 : 0));
        dest.writeInt(participantAgeGroup);
        dest.writeString(participantDepartment);
        dest.writeByte((byte) (isManager ? 1 : 0));
        dest.writeInt(numbEmployees);
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