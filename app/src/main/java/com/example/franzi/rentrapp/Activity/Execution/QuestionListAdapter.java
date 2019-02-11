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
        selectedAnswers = new ArrayList<Integer>();
        //create new view
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        //textview for questiontext
        TextView tvQuestion = (TextView) convertView.findViewById(R.id.tvQuestion);
        //put question text in according textview
        tvQuestion.setText(question.getQuestionText());
        //RadioButtons from Questionnaire_Item (to create List)
        radioButton1 = (RadioButton) convertView.findViewById(R.id.rbtnValue5);
        radioButton2 = (RadioButton) convertView.findViewById(R.id.rbtnValue4);
        radioButton3 = (RadioButton) convertView.findViewById(R.id.rbtnValue3);
        radioButton4 = (RadioButton) convertView.findViewById(R.id.rbtnValue2);
        radioButton5 = (RadioButton) convertView.findViewById(R.id.rbtnValue1);


        //find radio group and set onchangelistener
        radioGroup = (RadioGroup) convertView.findViewById(R.id.rbtnGr);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
                // get checked radio button by ID
                RadioButton checkedRadioButton = (RadioButton) radioGroup.findViewById(checkedID);
                boolean isChecked = checkedRadioButton.isChecked();
                checkedRadioButton.setChecked(true);
                question.setSelected(true);
                //find answer value by checking checkedID
                if (isChecked) {

                    if (radioButton1.getId() == checkedID) {

                        question.setSelectedValue(5);
                        selectedAnswers.add(5);
                    }
                    if (radioButton2.getId() == checkedID) {
                        question.setSelectedValue(4);
                        selectedAnswers.add(4);
                    }
                    if (radioButton3.getId() == checkedID) {
                        question.setSelectedValue(3);
                        selectedAnswers.add(3);
                    }
                    if (radioButton4.getId() == checkedID) {
                        question.setSelectedValue(2);
                        selectedAnswers.add(2);
                    }
                    if (radioButton5.getId() == checkedID) {
                        question.setSelectedValue(1);
                        selectedAnswers.add(1);
                    }


                }

            }
        });





        if (question.isSelected()) {
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




    public boolean filledOutCompletely() {
        boolean check = true;
        for(Question question  : mList){
            if(!(question.isSelected())){
                check = false;
            }
        }
        return check;
    }




}