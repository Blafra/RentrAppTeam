package com.example.franzi.rentrapp;

public class Question {

    private int questionID;
    private String text;
    private boolean systemIntroduction;
    private boolean existingSystem;
    private boolean categoryIndiv;
    private boolean categoryOrga;
    private boolean categorySys;
    private boolean branched;


    //Constructor
    public Question(int id, String text, boolean systemIntroduction, boolean existingSystem,boolean branched, boolean categoryIndiv, boolean categoryOrga, boolean categorySys){
        this.questionID = id;
        this.text = text;
        this.systemIntroduction = systemIntroduction;
        this.existingSystem = existingSystem;
        this.branched = branched;
        this.categoryIndiv = categoryIndiv;
        this.categoryOrga = categoryOrga;
        this.categorySys = categorySys;
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

    public boolean isSystemIntroduction() {
        return systemIntroduction;
    }


    public boolean isExistingSystem() {
        return existingSystem;
    }


    public boolean isBranched() {
        return branched;
    }

    public boolean isCategoryIndiv() {
        return categoryIndiv;
    }

    public boolean isCategoryOrga() {
        return categoryOrga;
    }

    public boolean isCategorySys() {
        return categorySys;
    }
    //Weiter Methonden
}
