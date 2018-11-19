package com.example.franzi.rentrapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStart;

    Survey sv;
    SpecificSurvey ss;
    Question[] questions = new Question[23];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Setup des Surveys inkl. Questions --> Vorablösung

        //Dummy Daten zum rumspielen


        boolean[] questionConfig = {true, false, false, false, false, false};
        questions[0] = new Question(1, "Ich interessiere mich für Computer und IT.", 1);
        questions[1] = new Question(1, "Ich bin gegenüber neuen Technologien positiv eingestellt.Ich bin gegenüber neuen Technologien positiv eingestellt.", 1);
        questions[2] = new Question(1, "Ich bin gegenüber der Einführung dieses Systems positiv eingestellt.", 1);
        questions[3] = new Question(1, "Ich fühle mich in der Lage das System zielgerichtet zu nutzen.", 1);
        questions[4] = new Question(1, "Meine erworbenen Kompetenzen kann ich im neuen System nutzen.", 1);
        questions[5] = new Question(1, "Ich habe mit dem System bereits Erfahrungen gesammelt.", 1);
        questions[6] = new Question(1, "Mein Unternehmen ist daran interessiert innovative Lösungen zu verwenden.", 2);
        questions[7] = new Question(1, "Es ist einfach neue Ideen im Unternehmen umzusetzen.", 2);
        questions[8] = new Question(1, "Das Management fördert die Einführung des Systems.", 2);
        questions[9] = new Question(1, "Das IT-System hat in unserem Unternehmen ein positives Image.", 2);
        questions[10] = new Question(1, "Das Projekt zur Einführung des Systems hat in unserem Unternehmen einen guten Ruf.", 2);
        questions[11] = new Question(1, "Ich kenne die Prozesse meines Aufgabengebiets, die durch das System unterstützt werden.", 2);
        questions[12] = new Question(1, "Ich kann meine Aufgaben ohne Nutzung dieses Systems erledigen. ", 2);
        questions[13] = new Question(1, "Dabei verwende ich folgende Alternativen:", 2);
        questions[14] = new Question(1, "Von mir wird erwartet das System zu nutzen.", 2);
        questions[15] = new Question(1, "Ich weiß an wen ich mich bei Problemen mit dem System wenden kann.", 2);
        questions[16] = new Question(1, "Die Hilfestellung, die ich erhalte, ist verständlich.", 2);

        questions[17] = new Question(1, "Ich konnte das neue System bereits testen. ", 3);
        questions[18] = new Question(1, "Ich empfinde die Systemqualität (Output, Geschwindigkeit, Usability…) als sehr gut.", 3);
        questions[19] = new Question(1, "Das System arbeitet schnell und ohne große Verzögerungen.", 3);
        questions[20] = new Question(1, "Das System ist intuitiv bedienbar.", 3);
        questions[21] = new Question(1, "Das System gibt mir verständliche Hilfestellung bei Problemen.", 3);
        questions[22] = new Question(1, "Das neue System ist besser als das vorherige.", 3);

        //Konfiguration der Umfrage (wird abgefragt bei Umfrageerstellung)
        boolean[] config = {true, false};

        sv = new Survey("LALA", "SAP", "S4HANA", "ERP", "Neueinführung", questions, config);
        ss = new SpecificSurvey(1, questions);
        sv.addSpecificSurvey(ss);


        btnStart = (Button) findViewById(R.id.btnStart);
        /* OnClickListener verwaltet das Klicken auf den Button */
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Individuell.class);
        intent.putExtra("Specific_Survey", ss);
        startActivity(intent);
        this.finish();
    }

    public static boolean filledOutCompletely(ArrayList<RadioButton> rbtnList) {
        //Prüfen ob alle Fragen beantwortet wurden

        int count = 0;
        boolean somethingChecked = false;

        for (RadioButton rbtn : rbtnList) {

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



    public static void saveQuestionResultValues (ArrayList<RadioButton> rbtnList, SpecificSurvey ss){


        //Liste durchlaufen und Antworten speichern

        int resultValue=5;
        int answerIdx = ss.getCurrentAnswerIdx();

        for(RadioButton rbtn : rbtnList){

            if(rbtn.isChecked()){
                ss.setAnswerArrayValues((answerIdx),resultValue);
                answerIdx++;
            }

            if(resultValue==1){
                resultValue=5;
            } else {
                resultValue--;
            }
        }

        //Speichern wo im Answer Array wir uns befinden
        ss.setCurrentAnswerIdx(answerIdx);


    }

  }







