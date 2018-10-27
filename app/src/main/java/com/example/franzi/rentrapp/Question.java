package com.example.franzi.rentrapp;

public class Question {

    private int questionID;
    private String text;
    private boolean systemIntroduction;
    private boolean existingSystem;
    private boolean branched;


    //Constructor
    public Question(int id, String text, boolean systemIntroduction, boolean existingSystem,boolean branched){
        this.questionID = id;
        this.text = text;
        this.systemIntroduction = systemIntroduction;
        this.existingSystem = existingSystem;
        this.branched = branched;
    }

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


    //Weiter Methonden

    public boolean isSystemIntroduction() {
        return systemIntroduction;
    }


    public boolean isExistingSystem() {
        return existingSystem;
    }


    public boolean isBranched() {
        return branched;
    }

}
