package com.example.franzi.rentrapp.Activity.Execution;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;


import com.example.franzi.rentrapp.Model.Question;
import com.example.franzi.rentrapp.Model.SpecificSurvey;
import com.example.franzi.rentrapp.R;

import java.util.ArrayList;
import java.util.List;

public class QuestionListAdapter extends ArrayAdapter<Question> {

    private Context mContext;
    int mResource;


    public RadioButton radioButton1;
    public RadioButton radioButton2;
    public RadioButton radioButton3;
    public RadioButton radioButton4;
    public RadioButton radioButton5;

    List<RadioButton> radioButtonList = new ArrayList<>();


    //Default Constructor
    public QuestionListAdapter(Context context, int resource, List<Question> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get question text
        String questionText = getItem(position).getQuestionText();

        //create the question object with the text
        Question question = new Question(questionText);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvQuestion = (TextView) convertView.findViewById(R.id.tvQuestion);

        //RadioButtons aus Questionnaire_Item um Liste erstellen zu können
        radioButton1 = (RadioButton) convertView.findViewById(R.id.rbtnValue5);
        radioButton2 = (RadioButton) convertView.findViewById(R.id.rbtnValue4);
        radioButton3 = (RadioButton) convertView.findViewById(R.id.rbtnValue3);
        radioButton4 = (RadioButton) convertView.findViewById(R.id.rbtnValue2);
        radioButton5 = (RadioButton) convertView.findViewById(R.id.rbtnValue1);

        radioButtonList = createList();

        tvQuestion.setText(questionText);

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

    private void handleRadioButtons(SpecificSurvey ss){


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
