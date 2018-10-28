package com.example.franzi.rentrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Individuell extends AppCompatActivity implements View.OnClickListener {

    Button btnWeiter1;
    SpecificSurvey ss;
    ArrayList<TextView> tvList = new ArrayList<>();

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
        Intent intent = new Intent(this, Organisatorisch.class);
        startActivity(intent);
        this.finish();
    }
}
