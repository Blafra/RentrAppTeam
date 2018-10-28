package com.example.franzi.rentrapp;

import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Individuell extends AppCompatActivity implements View.OnClickListener {

    Button btnWeiter1;
    SpecificSurvey ss;
    ArrayList<TextView> tvList = new ArrayList<>();
    ArrayList<RadioButton> rbtnList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individuell);

        //Specific Survey Object von vorhergehender Activity holen
        Intent intent = getIntent();
        ss = intent.getParcelableExtra("Specific_Survey");

        //Fragen ausfüllen
        //TextView in Liste zusammenfassen
        tvList.add((TextView) findViewById(R.id.tvQ1_7a));
        tvList.add((TextView) findViewById(R.id.tvQ1_6));
        tvList.add((TextView) findViewById(R.id.tvQ1_4));
        tvList.add((TextView) findViewById(R.id.tvQ1_3a));
        tvList.add((TextView) findViewById(R.id.tvQ1_2));
        tvList.add((TextView) findViewById(R.id.tvQ1_1));

        //Setzte alle Text Views gleich den noch nicht beantworteten Fragen im Fragenarray
        for(TextView tv : tvList){
            tv.setText(ss.getQuestionArray()[ss.getCurrentQuestionIdx()].getText());
            ss.setCurrentQuestionIdx(ss.getCurrentQuestionIdx()+1);
        }


        //Button Listener
        btnWeiter1 = (Button)findViewById(R.id.btnWeiter1);
        /* OnClickListener verwaltet das Klicken auf den Button */
        btnWeiter1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //Speichern der Antwortwerte im Antwortarray
        //Radio Buttons in Liste einfügen
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_7a_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_7a_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_7a_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_7a_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_7a_5));

        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_6_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_6_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_6_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_6_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_6_5));


        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_4_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_4_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_4_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_4_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_4_5));

        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_3a_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_3a_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_3a_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_3a_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_3a_5));

        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_2_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_2_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_2_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_2_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_2_5));

        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_1_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_1_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_1_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_1_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ1_1_5));

        MainActivity.saveQuestonResultValues(rbtnList,ss,getApplication());

        //Nächste Seite aufrufen
        Intent intent = new Intent(this, Organisatorisch.class);
        startActivity(intent);
        this.finish();

        //Speichern der Antwortwerte im Antwortarray
    }


}
