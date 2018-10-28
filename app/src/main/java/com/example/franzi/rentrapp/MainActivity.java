package com.example.franzi.rentrapp;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnStart;

    Survey sv;
    SpecificSurvey ss;
    Question[] questions = new Question[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Setup des Surveys inkl. Questions --> Vorablösung

        //Dummy Daten zum rumspielen


        boolean[] questionConfig = {true,false,false,false,false,false};
        questions[0] = new Question(1,"Ich interessiere michh für Computer und IT.", questionConfig);
        questions[1] = new Question(1,"Ich bin gegenüber neuen Technologien positiv eingestellt.Ich bin gegenüber neuen Technologien positiv eingestellt.", questionConfig);
        questions[2] = new Question(1,"Ich bin gegenüber der Einführung dieses Systems positiv eingestellt.", questionConfig);
        questions[3] = new Question(1,"Ich fühle mich in der Lage das System zielgerichtet zu nutzen.", questionConfig);
        questions[4] = new Question(1,"Meine erworbenen Kompetenzen kann ich im neuen System nutzen.", questionConfig);
        questions[5] = new Question(1,"Ich habe mit dem System bereits Erfahrungen gesammelt.", questionConfig);

        //Konfiguration der Umfrage (wird abgefragt bei Umfrageerstellung)
        boolean[] config = {true,false};

        sv = new Survey("LALA", "SAP","S4HANA", "ERP","Neueinführung",questions,config);
        ss = new SpecificSurvey(1,questions);
        sv.addSpecificSurvey(ss);


        btnStart = (Button) findViewById(R.id.btnStart);
        /* OnClickListener verwaltet das Klicken auf den Button */
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Individuell.class);
        intent.putExtra("Specific_Survey",ss);
        startActivity(intent);
        this.finish();
    }

  }







