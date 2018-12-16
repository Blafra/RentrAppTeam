package com.example.franzi.rentrapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {

    private int QuestionID;
    private String QuestionText;
    private String QuestionCategory;        //Wert (1) Categroy Indiv. (2) CategoryOrga (3) Category System
    private String SystemCategory;

    //Test
    //Constructor
    public Question(int id, String text, String questionCategory, String SystemCategory){
        this.QuestionID = id;
        this.QuestionText = text;
        this.QuestionCategory=questionCategory;
        this.SystemCategory = SystemCategory;
    }


    protected Question(Parcel in) {
        QuestionID = in.readInt();
        QuestionText = in.readString();
        QuestionCategory = in.readString();
    }

    public Question (String questionText){
        this.QuestionText = questionText;
    }

    public Question(){

    }


    //Parcel
    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    //Getter & Setter
    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int QuestionID){
        this.QuestionID = QuestionID;
    }

    public String getQuestionText() {
        return QuestionText;
    }

    public void setQuestionText(String QuestionText) {
        this.QuestionText = QuestionText;
    }

    public String getQuestionCategory() {
        return QuestionCategory;
    }

    public void setQuestionCategory(String QuestionCategory) {
        this.QuestionCategory = QuestionCategory;
    }

    public String getSystemCategory() {
        return SystemCategory;
    }

    public void setSystemCategory(String SystemCategory) {
        SystemCategory = SystemCategory;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(QuestionID);
        dest.writeString(QuestionText);
        dest.writeString(QuestionCategory);
    }


    //Weiter Methonden
}
