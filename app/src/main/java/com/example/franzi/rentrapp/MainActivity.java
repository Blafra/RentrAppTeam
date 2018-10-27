package com.example.franzi.rentrapp;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Survey sv;
    SpecificSurvey ss;
    Question[] questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Dummy Daten zum rumspielen

        questions = new Question[2];
        questions[0].setText("Wie toll findest du unsere App?");
        questions[1].setText("Hattest du jemals so viel Spaß wie heute?");
        boolean[] config = {true,false};

        sv = new Survey("LALA", "SAP","S4HANA", "ERP","Neueinführung",questions,config);
        ss = new SpecificSurvey(1,questions);

        TextView questionText = (TextView) findViewById(R.id.questionTextView);
        questionText.setText(questions[0].getText());

        //Listener und so

        final Button button = findViewById(R.id.saveBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Prüfen welcher Button ausgewählt wurde
                RadioButton[] rb = new RadioButton[5];
                rb[0] = (RadioButton) findViewById(R.id.radioButton);
                rb[1] = (RadioButton) findViewById(R.id.radioButton2);
                rb[2] = (RadioButton) findViewById(R.id.radioButton3);
                rb[3] = (RadioButton) findViewById(R.id.radioButton4);
                rb[4] = (RadioButton) findViewById(R.id.radioButton5);

                for(int i=0;i<5;i++){
                    if(rb[i].isChecked()){
                        ss.getAnswerArray()[0] = i;
                    }
                }

                TextView questionText = (TextView) findViewById(R.id.questionTextView);
                questionText.setText(questions[1].getText());

                TextView resultText = (TextView) findViewById(R.id.resultTextView);

            }
        });
    }






}
