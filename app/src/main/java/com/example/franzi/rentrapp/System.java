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

public class System extends AppCompatActivity implements View.OnClickListener {

    Button btnWeiter3;
    SpecificSurvey ss;
    ArrayList<TextView> tvList = new ArrayList<>();
    ArrayList<RadioButton> rbtnList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);

        //Specific Survey Object von vorhergehender Activity holen
        Intent intent = getIntent();
        ss = intent.getParcelableExtra("Specific_Survey3");

        //Fragen ausfüllen
        //TextView in Liste zusammenfassen
        tvList.add((TextView) findViewById(R.id.tvQ3_21));
        tvList.add((TextView) findViewById(R.id.tvQ3_22));
        tvList.add((TextView) findViewById(R.id.tvQ3_23));
        tvList.add((TextView) findViewById(R.id.tvQ3_24));
        tvList.add((TextView) findViewById(R.id.tvQ3_25));
        tvList.add((TextView) findViewById(R.id.tvQ3_28));

        //Setzte alle Text Views gleich den noch nicht beantworteten Fragen im Fragenarray
        for(TextView tv : tvList){
            tv.setText(ss.getQuestionArray()[ss.getCurrentQuestionIdx()].getQuestionText());
            ss.setCurrentQuestionIdx(ss.getCurrentQuestionIdx()+1);
        }

        btnWeiter3 = (Button)findViewById(R.id.btnWeiter3);
        btnWeiter3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //Speichern der Antwortwerte im Antwortarray
        //Radio Buttons in Liste einfügen

        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_21_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_21_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_21_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_21_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_21_5));

        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_22_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_22_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_22_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_22_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_22_5));

        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_23_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_23_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_23_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_23_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_23_5));

        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_24_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_24_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_24_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_24_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_24_5));

        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_25_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_25_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_25_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_25_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_25_5));

        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_28_1));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_28_2));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_28_3));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_28_4));
        rbtnList.add((RadioButton) findViewById(R.id.rbtnQ3_28_5));


        //Ergebnisse spiecher und überprüfen ob alles Ausgefüllt wurde
        boolean filledOutCompletely = MainActivity.filledOutCompletely(rbtnList);

        if(filledOutCompletely) {
            //Antworten speichern
            MainActivity.saveQuestionResultValues(rbtnList,ss);
            Intent intent = new Intent(this, ErgebnisIndividuell.class);
            intent.putExtra("Specific_Survey4", ss);
            startActivity(intent);
            this.finish();
        } else{
            Toast.makeText(getApplication().getBaseContext(),"Es sind nicht alle Fragen beantwortet",Toast.LENGTH_SHORT).show();
        }
    }
}
