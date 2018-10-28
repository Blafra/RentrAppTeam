package com.example.franzi.rentrapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {

    private int questionID;
    private String text;
    private int questionCategory;        //Wert (1) Categroy Indiv. (2) CategoryOrga (3) Category System

    //Test
    //Constructor
    public Question(int id, String text, int questionCategory){
        this.questionID = id;
        this.text = text;
        this.questionCategory=questionCategory;
    }


    protected Question(Parcel in) {
        questionID = in.readInt();
        text = in.readString();
        questionCategory = in.readInt();
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
        return questionID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getquestionCategory() {
        return questionCategory;
    }

    public void setquestionCategory(int questionCategory) {
        this.questionCategory = questionCategory;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(questionID);
        dest.writeString(text);
        dest.writeInt(questionCategory);
    }


    //Weiter Methonden
}
