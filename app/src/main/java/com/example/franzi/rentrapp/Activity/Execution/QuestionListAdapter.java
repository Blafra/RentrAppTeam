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
    List<Integer> resultList = new ArrayList<>();
    List<Boolean> isCheckedList = new ArrayList<>();
    List<Question> questionList = new ArrayList<>();


    public RadioButton radioButton1;
    public RadioButton radioButton2;
    public RadioButton radioButton3;
    public RadioButton radioButton4;
    public RadioButton radioButton5;

    List<RadioButton> radioButtonList = new ArrayList<>();


    //Default Constructor
    public QuestionListAdapter(Context context, int resource, List<Question> questionList) {
        super(context, resource, questionList);
        mContext = context;
        mResource = resource;
        this.questionList = questionList;
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


        tvQuestion.setText(questionText);


        onRadioButtonClicked(convertView);


        return convertView;


    }


    private void onRadioButtonClicked(View view) {

        int value = 0;
        //is the button checked?
        boolean checked = ((RadioButton) view).isChecked();

        isCheckedList.add(checked);

        //check which radio button was clicked
        switch (view.getId()) {
            case R.id.rbtnValue5:
                if (checked) {
                    value = 5;
                    resultList.add(value);
                    break;
                }
            case R.id.rbtnValue4:
                if (checked) {
                    value = 4;
                    resultList.add(value);
                    break;
                }
            case R.id.rbtnValue3:
                if (checked) {
                    value = 3;
                    resultList.add(value);
                    break;
                }
            case R.id.rbtnValue2:
                if (checked) {
                    value = 2;
                    resultList.add(value);
                    break;
                }
            case R.id.rbtnValue1:
                if (checked) {
                    value = 1;
                    resultList.add(value);
                    break;
                }

        }
    }

    public boolean allChecked() {
        int counterChecked = 0;
        int counterQuestion = 0;
        boolean checkValue;
        for (boolean b : isCheckedList) {
            if (b == true) {
                counterChecked++;
            }
        }
        for (Question q : questionList) {
            counterQuestion++;
        }
        if (counterChecked == counterQuestion) {
            checkValue = true;
        } else checkValue = false;
        return checkValue;
    }


}

    /*public boolean filledOutCompletely() {
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
*/

