package com.example.franzi.rentrapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class IndividuellesErgebnis extends AppCompatActivity {

    SpecificSurvey ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individuelles_ergebnis);

        //Specific Survey Object von vorhergehender Activity holen
        Intent intent = getIntent();
        ss = intent.getParcelableExtra("Specific_Survey4");

        //Berechnung der Ergebnisses

        double[] results = ss.calcResult();

        //Ergebnisse in Textfeldern anzeigen lassen

        TextView tvResultTotal = (TextView) findViewById(R.id.tvResultTotal);
        TextView tvResultInd = (TextView) findViewById(R.id.tvResultInd);
        TextView tvResultOrg = (TextView) findViewById(R.id.tvResultOrg);
        TextView tvResultSys = (TextView) findViewById(R.id.tvResultSys);

        tvResultTotal.setText(Double.toString(results[0]));
        tvResultInd.setText(Double.toString(results[1]));
        tvResultOrg.setText(Double.toString(results[2]));
        tvResultSys.setText(Double.toString(results[3]));

        //Nächste Seite aufrufen: Individuelles Ergebnis

        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();

    }
}
