package com.example.franzi.rentrapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {

    private int questionID;
    private String text;
    private boolean[] questionProperties = new boolean[6];        //(0) System Indroduciton (1) Existing System (2) Categroy Indiv. (3) CategoryOrga (4) Category System (5) Branched


    //Test
    //Constructor
    public Question(int id, String text, boolean[] questionProperties){
        this.questionID = id;
        this.text = text;
        this.questionProperties=questionProperties;
    }


    protected Question(Parcel in) {
        questionID = in.readInt();
        text = in.readString();
        questionProperties = in.createBooleanArray();
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

    public boolean[] getQuestionProperties() {
        return questionProperties;
    }

    public void setQuestionProperties(boolean[] questionProperties) {
        this.questionProperties = questionProperties;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(questionID);
        dest.writeString(text);
        dest.writeBooleanArray(questionProperties);
    }


    //Weiter Methonden
}
