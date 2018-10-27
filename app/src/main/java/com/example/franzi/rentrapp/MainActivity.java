package com.example.franzi.rentrapp;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Survey sv;
    SpecificSurvey ss;
    Question[] questions = new Question[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Dummy Daten zum rumspielen

        questions[0] = new Question(1,"Wie toll findest du unsere App?", true,false,false,true,false,false);
        questions[1] = new Question(1,"Hattest du jemals so viel Spaß wie heute?", true,false,false,true,false,false);
        questions[2] = new Question(1,"Der erste Durchbruch war der Hammer :-)", true,false,false,true,false,false);
        questions[3] = new Question(1,"Übel geile APP ihr geilen Leute!!", true,false,false,true,false,false);

        boolean[] config = {true,false};

        sv = new Survey("LALA", "SAP","S4HANA", "ERP","Neueinführung",questions,config);
        ss = new SpecificSurvey(1,questions);

        TextView questionText = (TextView) findViewById(R.id.questionTextView);
        questionText.setText(questions[0].getText());

        //Listener und so

        final Button button = findViewById(R.id.saveBtn);

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                int currentQuestion = ss.getCurrentQuestionIdx();
                TextView questionText = (TextView) findViewById(R.id.questionTextView);

                //Prüft ob noch Fragen im Katalog vorhanden sind
                if (currentQuestion < questions.length-1) {

                    //Prüfen welcher Button ausgewählt wurde & ins Antwortarray eintragen
                    RadioButton[] rb = new RadioButton[5];
                    rb[0] = (RadioButton) findViewById(R.id.radioButton);
                    rb[1] = (RadioButton) findViewById(R.id.radioButton2);
                    rb[2] = (RadioButton) findViewById(R.id.radioButton3);
                    rb[3] = (RadioButton) findViewById(R.id.radioButton4);
                    rb[4] = (RadioButton) findViewById(R.id.radioButton5);

                    for (int i = 0; i < 5; i++) {
                        if (rb[i].isChecked()) {
                            ss.getAnswerArray()[currentQuestion] = i + 1;
                        }
                    }
                    Toast.makeText(getApplicationContext(),"Gespeichert",Toast.LENGTH_LONG).show();
                    //Get the next Question
                    currentQuestion++;
                    ss.setCurrentQuestionIdx(currentQuestion);
                    //Check if question left
                    questionText.setText(questions[currentQuestion].getText());

                    ss.calcResult();



                    TextView resultText = (TextView) findViewById(R.id.resultTextView);
                    double resultInt = ss.getResultTotal();
                    String resultSting = Double.toString(resultInt);
                    resultText.setText(resultSting);

                } else {
                    questionText.setText("Keine weiteren Fragen mehr");
                }
            }
        });

    }






}
