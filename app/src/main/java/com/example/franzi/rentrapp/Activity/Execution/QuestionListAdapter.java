package com.example.franzi.rentrapp.Activity.Execution;

import android.content.Context;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.franzi.rentrapp.Model.Question;
import com.example.franzi.rentrapp.Model.SpecificSurvey;
import com.example.franzi.rentrapp.R;

import java.util.ArrayList;
import java.util.List;

public class QuestionListAdapter extends ArrayAdapter<Question> {

    private Context mContext;
    int mResource;

    //all radio buttons
    public RadioButton radioButton1;
    public RadioButton radioButton2;
    public RadioButton radioButton3;
    public RadioButton radioButton4;
    public RadioButton radioButton5;
    public RadioGroup radioGroup;
    // list for answer values
    public static ArrayList<Integer> selectedAnswers;
    //questions
    List<Question> mList;

    // int itemposition;

   List<RadioButton> radioButtonList = new ArrayList<>();

  //int i = 0;

    //Default Constructor
    public QuestionListAdapter(Context context, int resource, List<Question> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        mList = objects;
    }



    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Question question = getItem(position);
        //get question text
        //String questionText = getItem(position).getQuestionText();
        //List to save answer value
        selectedAnswers = new ArrayList<Integer>();
        //create the question object with the text
       // final Question question = new Question(questionText);
        //create new view
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        //textview for questiontext
        TextView tvQuestion = (TextView) convertView.findViewById(R.id.tvQuestion);
        //put question text in according textview
       // tvQuestion.setText(questionText);
        tvQuestion.setText(question.getQuestionText());
        //RadioButtons from Questionnaire_Item (to create List)
        radioButton1 = (RadioButton) convertView.findViewById(R.id.rbtnValue5);
        radioButton2 = (RadioButton) convertView.findViewById(R.id.rbtnValue4);
        radioButton3 = (RadioButton) convertView.findViewById(R.id.rbtnValue3);
        radioButton4 = (RadioButton) convertView.findViewById(R.id.rbtnValue2);
        radioButton5 = (RadioButton) convertView.findViewById(R.id.rbtnValue1);

     //   radioButtonList = createList();
        //find radio group and set onchangelistener
        radioGroup = (RadioGroup)convertView.findViewById(R.id.rbtnGr);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                // get checked radio button by ID
               RadioButton checkedRadioButton = (RadioButton) radioGroup.findViewById(checkedID);
                boolean isChecked = checkedRadioButton.isChecked();
                checkedRadioButton.setChecked(true);
                question.setSelected(true);
                //find answer value by checking checkedID
                if(isChecked){
                   // i = checkedID;
                    if(radioButton1.getId() == checkedID){

                        question.setSelectedValue(5);
                        selectedAnswers.add(5);
                    }
                    if(radioButton2.getId() == checkedID){
                        question.setSelectedValue(4);
                        selectedAnswers.add(4);
                    }
                    if(radioButton3.getId() == checkedID){
                        question.setSelectedValue(3);
                        selectedAnswers.add(3);
                    }
                    if(radioButton4.getId() == checkedID){
                        question.setSelectedValue(2);
                        selectedAnswers.add(2);
                    }
                    if(radioButton5.getId() == checkedID){
                        question.setSelectedValue(1);
                        selectedAnswers.add(1);
                    }


                    /*String text = "Antwort: "+checkedID;
                    Toast toastq = Toast.makeText(mContext, text, Toast.LENGTH_LONG);
                    toastq.show();*/
                }
              // int position = (Integer) checkedRadioButton.getTag();
               // itemposition = position;
            }
        });




      /* radioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    selectedAnswers.add(5);
                    Toast toastq = Toast.makeText(mContext, "Die Antwort 5 wurde gespeichert", Toast.LENGTH_LONG);
                    toastq.show();
                }
            }
        });
        radioButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    selectedAnswers.add(4);
                    Toast toastq = Toast.makeText(mContext, "Die Antwort 4 wurde gespeichert", Toast.LENGTH_LONG);
                    toastq.show();
                                }
            }
        });
        radioButton3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    selectedAnswers.add(3);
                }
            }
        });
        radioButton4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    selectedAnswers.add(2);
                }
            }
        });
        radioButton5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    selectedAnswers.add(1);
                }
            }
        });*/



     /*  if(radioButton1.isSelected() ){
            radioButton1.setChecked(true);
        }
       if(radioButton2.isSelected() ){
            radioButton2.setChecked(true);
        }
        if(radioButton3.isSelected()){
            radioButton3.setChecked(true);
        }
        if(radioButton4.isSelected()){
            radioButton4.setChecked(true);
        }
        if(radioButton5.isSelected()){
            radioButton5.setChecked(true);
        }*/

       /* int selectedID = radioGroup.getCheckedRadioButtonId();
        radioButton1 = (RadioButton)convertView.findViewById(selectedID);
        Log.d("STATE", "Id ist"+selectedID);*/


       if(question.isSelected()) {
            if (question.getSelectedValue() == 5) {
                radioButton1.setChecked(true);
            }
            if (question.getSelectedValue() == 4) {
                radioButton2.setChecked(true);
            }
            if (question.getSelectedValue() == 3) {
                radioButton3.setChecked(true);
            }
            if (question.getSelectedValue() == 2) {
                radioButton4.setChecked(true);
            }
            if (question.getSelectedValue() == 1) {
                radioButton5.setChecked(true);
            }
        }
        return convertView;


    }

    //Radio-ButtonList erstellen
    private List<RadioButton> createList() {
        radioButtonList.add(radioButton1);
        radioButtonList.add(radioButton2);
        radioButtonList.add(radioButton3);
        radioButtonList.add(radioButton4);
        radioButtonList.add(radioButton5);
        return radioButtonList;
    }

    private void handleRadioButtons( View convertView){

    }



    public boolean filledOutCompletely() {
        //Prüfen ob alle Fragen beantwortet wurden

        int count = 0;
        boolean somethingChecked = false;

        for (RadioButton rbtn : radioButtonList) {

            //Prüfen ob keines der Felder angeklickt wurde
            if (count > 4 && somethingChecked == false) {
                return false;
            }

            //Eines der Felder (von 5) wurde angeklickt --> Reste für nächste 5 Felder
            if (count > 4 && somethingChecked == true) {
                count = -1;
                somethingChecked = false;
            }

            //Prüfen ob Feld angeklickt wurde
            if (rbtn.isChecked()) {
                somethingChecked = true;
            }
            count++;

        }
        return true;
    }



    public void saveQuestionResultValues(SpecificSurvey ss) {

        //Liste durchlaufen und Antworten speichern

        int resultValue = 5;
        int answerIdx = ss.getCurrentAnswerIdx();

        for (RadioButton rbtn : radioButtonList) {

            if (rbtn.isChecked()) {
                ss.setAnswerArrayValues(answerIdx, resultValue);
                answerIdx++;
            }

            if (resultValue == 1) {
                resultValue = 5;
            } else {
                resultValue--;
            }
        }

        //Speichern wo im Answer Array wir uns befinden
        ss.setCurrentAnswerIdx(answerIdx);

    }

}
