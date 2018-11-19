package com.example.franzi.rentrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Organisatorisch extends AppCompatActivity implements View.OnClickListener {

    Button btnWeiter2;
    SpecificSurvey ss;
    ArrayList<TextView> tvList = new ArrayList<>();
    ArrayList<RadioButton> rbtnList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisatorisch);

        //Specific Survey Object von vorhergehender Activity holen
        Intent intent = getIntent();
        ss = intent.getParcelableExtra("Specific_Survey2");


        //Fragen ausfüllen
        //TextView in Liste zusammenfassen
        tvList.add((TextView) findViewById(R.id.tvQ2_8));
        tvList.add((TextView) findViewById(R.id.tvQ2_9));
        tvList.add((TextView) findViewById(R.id.tvQ2_10_b));
        tvList.add((TextView) findViewById(R.id.tvQ2_11));
        tvList.add((TextView) findViewById(R.id.tvQ2_12));
        tvList.add((TextView) findViewById(R.id.tvQ2_13));
        tvList.add((TextView) findViewById(R.id.tvQ2_14));
        tvList.add((TextView) findViewById(R.id.tvQ2_15));
        tvList.add((TextView) findViewById(R.id.tvQ2_16));
        tvList.add((TextView) findViewById(R.id.tvQ2_17));
        tvList.add((TextView) findViewById(R.id.tvQ2_18));

//Setzte alle Text Views gleich den noch nicht beantworteten Fragen im Fragenarray
        for(TextView tv : tvList){
            tv.setText(ss.getQuestionArray()[ss.getCurrentQuestionIdx()].getQuestionText());
            ss.setCurrentQuestionIdx(ss.getCurrentQuestionIdx()+1);
        }

        //Button Listener
        btnWeiter2 = (Button)findViewById(R.id.btnWeiter2);
        /* OnClickListener verwaltet das Klicken auf den Button */
        btnWeiter2.setOnClickListener(this);
}

    @Override
    public void onClick(View v) {
         //Speichern der Antwortwerte im Antwortarray
        //Radio Buttons in Liste einfügen

        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_8_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_8_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_8_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_8_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_8_5));

        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_9_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_9_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_9_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_9_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_9_5));

        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_10_b_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_10_b_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_10_b_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_10_b_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_10_b_5));

        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_11_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_11_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_11_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_11_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_11_5));

        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_12_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_12_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_12_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_12_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_12_5));

        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_13_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_13_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_13_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_13_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_13_5));

        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_14_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_14_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_14_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_14_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_14_5));

        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_15_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_15_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_15_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_15_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_15_5));

        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_16_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_16_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_16_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_16_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_16_5));

        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_17_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_17_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_17_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_17_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_17_5));

        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_18_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_18_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_18_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_18_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ2_18_5));

        //Ergebnisse spiecher und überprüfen ob alles Ausgefüllt wurde
        boolean filledOutCompletely = MainActivity.filledOutCompletely(rbtnList);

        if(filledOutCompletely) {
            //Antworten speichern
            MainActivity.saveQuestionResultValues(rbtnList,ss);
            Intent intent = new Intent(this, System.class);
            intent.putExtra("Specific_Survey3", ss);
            startActivity(intent);
            this.finish();
        } else {
            Toast.makeText(getApplication().getBaseContext(),"Es sind nicht alle Fragen beantwortet",Toast.LENGTH_SHORT).show();
        }
    }
}
